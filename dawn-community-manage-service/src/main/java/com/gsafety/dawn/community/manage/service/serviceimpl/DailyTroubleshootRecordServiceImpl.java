package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.common.util.DateUtil;
import com.gsafety.dawn.community.common.util.ExcelUtil;
import com.gsafety.dawn.community.manage.contract.model.BasicInformationModel;
import com.gsafety.dawn.community.manage.contract.model.DailyStatisticModel;
import com.gsafety.dawn.community.manage.contract.model.DailyTroubleFilterModel;
import com.gsafety.dawn.community.manage.contract.model.DailyTroubleshootRecordModel;
import com.gsafety.dawn.community.manage.contract.model.total.DailyStatisticPageModel;
import com.gsafety.dawn.community.manage.contract.model.total.DiagnosisCountModel;
import com.gsafety.dawn.community.manage.contract.model.total.PendingInvestigatModel;
import com.gsafety.dawn.community.manage.contract.model.total.PlotLinkageModel;
import com.gsafety.dawn.community.manage.contract.service.BasicInformationService;
import com.gsafety.dawn.community.manage.contract.service.DailyTroubleshootRecordService;
import com.gsafety.dawn.community.manage.service.datamappers.BasciInformationMapper;
import com.gsafety.dawn.community.manage.service.datamappers.DSourceDataMapper;
import com.gsafety.dawn.community.manage.service.datamappers.DailyTroubleshootRecordMapper;
import com.gsafety.dawn.community.manage.service.entity.BasicInformationEntity;
import com.gsafety.dawn.community.manage.service.entity.DSourceDataEntity;
import com.gsafety.dawn.community.manage.service.entity.DailyTroubleshootRecordEntity;
import com.gsafety.dawn.community.manage.service.entity.EpidemicPersonEntity;
import com.gsafety.dawn.community.manage.service.repository.BasicInformationRepository;
import com.gsafety.dawn.community.manage.service.repository.DSourceDataRepository;
import com.gsafety.dawn.community.manage.service.repository.DailyTroubleshootRecordRepository;
import com.gsafety.dawn.community.manage.service.repository.EpidemicPersonRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;


import static java.util.stream.Collectors.*;

/**
 * description:
 *
 * @outhor liujian
 * @create 2020 -02-08 12:53
 */
@Service
@Transactional
public class DailyTroubleshootRecordServiceImpl implements DailyTroubleshootRecordService {

    // 多租户
    @Value("${app.multiTenancy}")
    private String multiTenancy;

    // 体温
    @Value("${app.bodyTemperature}")
    private String bodyTemperature;

    /**
     * The Record mapper.
     */
    @Autowired
    DailyTroubleshootRecordMapper recordMapper;

    /**
     * The Record repository.
     */
    @Autowired
    DailyTroubleshootRecordRepository recordRepository;

    /**
     * The Epidemic person repository.
     */
    @Autowired
    EpidemicPersonRepository epidemicPersonRepository;

    // 社区id
    @Value("${app.commId}")
    private String commId;

    // 其他症状id
    @Value("${app.otherSymptomId}")
    private String otherSymptomId;

    // 分类诊疗医疗意见
    @Value("${app.medicalAdvice}")
    private String medicalAdvice;


    @Autowired
    private DSourceDataRepository dSourceDataRepository;

    @Autowired
    private DSourceDataMapper dSourceDataMapper;

    @Autowired
    BasciInformationMapper basciInformationMapper;

    @Autowired
    BasicInformationRepository basicInformationRepository;

    @Autowired
    BasicInformationService basicInformationService;

    // 日志
    private static Logger logger = LoggerFactory.getLogger(DailyTroubleshootRecordServiceImpl.class);

    // 时间
    private static final Timestamp TS = DateUtil.convertNowDate();

    // 一天开始时间
    private static final Timestamp STARTTIME = DateUtil.getStartTime();

    // 一天结束时间
    private static final Timestamp ENDTIME = DateUtil.getEndTime();

    // 男
    private static final String maleId = "3f265ff3-75b8-49f1-9669-4506535a500c";
    //女
    private static final String femaleId = "2ae58f9e-65f2-4f2a-8244-ac01d668b7b5";
    // 性别未知
    private static final String noSexId = "13a5633b-57fa-4850-9c36-61356bd99c50";

    @Override
    public DailyTroubleshootRecordModel addDailyRecord(DailyTroubleshootRecordModel dailyTroubleshootRecordModel) {

        // 今日记录
//        List<DailyTroubleshootRecordEntity> todayRecords = recordRepository.todayRecord(STARTTIME, ENDTIME);
//        List<DailyTroubleshootRecordEntity> collect = todayRecords.stream()
//                .filter(a ->
//                        a.getName().equals(dailyTroubleshootRecordModel.getName()) &&
//                        a.getPhone().equals(dailyTroubleshootRecordModel.getPhone()))
//                .collect(Collectors.toList());
//        if(!collect.isEmpty())
//            return null;

        // 可以重复
//        List<DailyTroubleshootRecordEntity> recordEntities = recordRepository.queryAllByNameAndPhone(dailyTroubleshootRecordModel.getName(), dailyTroubleshootRecordModel.getPhone());
//        if(!recordEntities.isEmpty())
//            return null;

        //
        DailyTroubleshootRecordEntity recordEntity = recordMapper.modelToEntity(dailyTroubleshootRecordModel);
        recordEntity.setId(UUID.randomUUID().toString());
        recordEntity.setCode(UUID.randomUUID().toString());
        recordEntity.setCreateTime(DateUtil.convertNowDate());
        recordEntity.setMultiTenancy(multiTenancy);
        DailyTroubleshootRecordEntity result = recordRepository.save(recordEntity);

        // 体温异常添加到其它数据库
        addEpidMic(result);

        // 往基本信息表插入数据
        addToBasicInformation(result);

        return recordMapper.entityToModel(result);
    }

    @Override
    public DailyTroubleshootRecordModel updateDailyRecord(DailyTroubleshootRecordModel dailyTroubleshootRecordModel) {
        boolean exists = recordRepository.existsById(dailyTroubleshootRecordModel.getId());
        if (exists) {
            dailyTroubleshootRecordModel.setCreateTime(TS);
            DailyTroubleshootRecordEntity recordEntity = recordRepository.saveAndFlush(recordMapper.modelToEntity(dailyTroubleshootRecordModel));
            // 往疫情表添加数据
            addEpidMic(recordEntity);
            //往基本信息表插入数据
            addToBasicInformation(recordEntity);
            return recordMapper.entityToModel(recordEntity);
        }
        return null;
    }

    @Override
    public List<DailyTroubleshootRecordModel> importTroubleshootRecord(MultipartFile multipartFile) {
        List<DailyTroubleshootRecordEntity> recordEntities = new ArrayList<>();
        try {
            String fileName = multipartFile.getOriginalFilename();
            String fileType = fileName.substring(fileName.lastIndexOf('.') + 1, fileName.length());
            List<Sheet> sheets = ExcelUtil.getExcelSheet(multipartFile.getInputStream(), fileType);
            for (int i = 0; i < sheets.size(); i++) {
                Sheet sheet = sheets.get(i);
                List<DailyTroubleshootRecordEntity> recordEntityList = parseSheetRecord(sheet);
                if (!recordEntityList.isEmpty())
                    recordEntities.addAll(recordEntityList);
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        List<DailyTroubleshootRecordEntity> result = saveRecord(recordEntities);
        // 往基本信息表插入数据
        // 往疫情表插入数据
        result.forEach(a -> {
            addToBasicInformation(a);
            addEpidMic(a);
        });

        return recordMapper.entitiesToModels(result);
    }

    // 存数据
    public List<DailyTroubleshootRecordEntity> saveRecord(List<DailyTroubleshootRecordEntity> recordEntities) {
        List<DailyTroubleshootRecordEntity> result = new ArrayList<>();
        for (int i = 0; i < recordEntities.size(); i++) {
            DailyTroubleshootRecordEntity recordEntity = recordEntities.get(i);
            if ("".equals(recordEntity.getName())
                    || "".equals(recordEntity.getPhone())
                    || recordEntity.getName() == null || recordEntity.getPhone() == null)
                continue;
            List<DailyTroubleshootRecordEntity> temps = recordRepository.queryAllByNameAndPhone(recordEntity.getName(), recordEntity.getPhone());
            if (temps.isEmpty())
                result.add(recordRepository.save(recordEntity));
        }
        return result;
    }


    /**
     * Parse sheet record list.
     *
     * @param sheet the sheet
     * @return the list
     */
    // 解析每个tab中的内容
    public List<DailyTroubleshootRecordEntity> parseSheetRecord(Sheet sheet) {
        if (sheet == null) {
            logger.error("tab页内容为空");
            return Collections.emptyList();
        }
        List<DailyTroubleshootRecordEntity> recordEntities = new ArrayList<>();
        int endRow = sheet.getPhysicalNumberOfRows();
        int firstSheetNumber = sheet.getFirstRowNum();
        for (int rowNum = firstSheetNumber + 1; rowNum < endRow; rowNum++) {
            Row row = sheet.getRow(rowNum);

            // 小区在系统不存在，不允许导入
            String plot = ExcelUtil.convertCellValueToString(row.getCell(6));

            List<DSourceDataEntity> dataList = dSourceDataRepository.findAllByName(plot);
            if (dataList.isEmpty())
                continue;
            plot = dataList.get(0).getId();

            // 其它症状
            String other = ExcelUtil.convertCellValueToString(row.getCell(12));
//            String dataIds = getDataIds(other);
//            if(dataIds != null){
//                other = dataIds.substring(0 , dataIds.length() - 1);
//            }


            // 分类诊疗意见
            String opinion = ExcelUtil.convertCellValueToString(row.getCell(13));
//            String dataIdO = getDataIds(opinion);
//            if(dataIdO != null){
//                opinion = dataIdO.substring(0 , dataIdO.length() - 1 );
//            }

            String name = ExcelUtil.convertCellValueToString(row.getCell(0));
            String idCard = ExcelUtil.convertCellValueToString(row.getCell(1));
            String sex = ExcelUtil.convertCellValueToString(row.getCell(2));
            String age = ExcelUtil.convertCellValueToString(row.getCell(3));
            String phone = ExcelUtil.convertCellValueToString(row.getCell(4));
            String address = ExcelUtil.convertCellValueToString(row.getCell(5));

            String build = ExcelUtil.convertCellValueToString(row.getCell(7));
            String unit = ExcelUtil.convertCellValueToString(row.getCell(8));
            String roomNo = ExcelUtil.convertCellValueToString(row.getCell(9));
            String tempture = ExcelUtil.convertCellValueToString(row.getCell(10));
            String contact = ExcelUtil.convertCellValueToString(row.getCell(11));


            String note = ExcelUtil.convertCellValueToString(row.getCell(14));
            if ("".equals(name) || "".equals(address) || "".equals(phone) || "".equals(build) || "".equals(unit))
                continue;
            if (name == null || address == null || phone == null || build == null || unit == null)
                continue;
            DailyTroubleshootRecordEntity recordEntity = new DailyTroubleshootRecordEntity();
            recordEntity.setId(UUID.randomUUID().toString());
            recordEntity.setCreateTime(TS);
            recordEntity.setMultiTenancy(multiTenancy);
            recordEntity.setAddress(address);

            recordEntity.setOtherSymptoms(other);
            recordEntity.setMedicalOpinion(opinion);
            recordEntity.setNote(note);

            if (!"".equals(age) && age != null) {
                recordEntity.setAge(Integer.valueOf(age));
            }

            if (sex == null || "".equals(sex)) {
                recordEntity.setSex(noSexId);
            } else {
                recordEntity.setSex(sex.equals("男") ? maleId : femaleId);
            }
            recordEntity.setContact(contact.equals("t"));
            recordEntity.setExceedTemp(tempture.equals("t"));
            recordEntity.setBuilding(build);
            recordEntity.setPlot(plot);
            recordEntity.setCode(UUID.randomUUID().toString());
            recordEntity.setIdentificationNumber(idCard);
            recordEntity.setUnitNumber(unit);
            recordEntity.setRoomNo(roomNo);
            recordEntity.setName(name);
            recordEntity.setPhone(phone);
            // 体温大于37.5 以及 已有相同数据不添加 未过滤
            recordEntities.add(recordEntity);
        }
        return recordEntities;
    }

    /**
     * Add epid mic epidemic person entity.
     *
     * @param dailyTroubleshootRecordEntity the daily troubleshoot record entity
     * @return the epidemic person entity
     */
    // 当体温大于37.3时 往Epidmicperson表中插入数据
    public void addEpidMic(DailyTroubleshootRecordEntity dailyTroubleshootRecordEntity) {
        if (dailyTroubleshootRecordEntity.isExceedTemp()) {
            EpidemicPersonEntity epidemicPersonEntity = new EpidemicPersonEntity();

            epidemicPersonEntity.setId(UUID.randomUUID().toString());
            epidemicPersonEntity.setSubmitTime(TS);
            epidemicPersonEntity.setGender(dailyTroubleshootRecordEntity.getSex());
            epidemicPersonEntity.setSubmitTime(dailyTroubleshootRecordEntity.getCreateTime());
            // epidemicPersonEntity.setUpdateTime(dailyTroubleshootRecordEntity.getCreateTime());
            epidemicPersonEntity.setMultiTenancy(multiTenancy);
            epidemicPersonEntity.setName(dailyTroubleshootRecordEntity.getName());
            epidemicPersonEntity.setMobileNumber(dailyTroubleshootRecordEntity.getPhone());

            epidemicPersonRepository.save(epidemicPersonEntity);
        }
    }

    // 获取ids
    public String getDataIds(String data) {
        if (!"".equals(data) || data != null) {
            String realOther = "";
            String[] split = data.split(",");
            for (int t = 0; t < split.length; t++) {
                List<DSourceDataEntity> allByName = dSourceDataRepository.findAllByName(split[t]);
                if (!allByName.isEmpty()) {
                    realOther += allByName.get(0).getId() + ",";
                }
            }
            return realOther;
        } else {
            return null;
        }
    }


    // 查询所有按小区划分
    @Override
    public Map<String, List<DailyTroubleshootRecordModel>> queryAll() {
        return recordMapper.entitiesToModels(recordRepository.findAll())
                .stream().collect(
                        groupingBy(DailyTroubleshootRecordModel::getPlot));
    }

    // 按小区过滤
    @Override
    public Map<String, List<DailyTroubleshootRecordModel>> filterByCommunity(List<String> communityIds) {
        return recordMapper.entitiesToModels(recordRepository.findAll())
                .stream()
                .filter(a -> communityIds.contains(a.getPlot()))
                .collect(groupingBy(DailyTroubleshootRecordModel::getPlot));
    }

    // 每个小区体温超标 > 37.3 人员
    @Override
    public Map<String, List<DailyTroubleshootRecordModel>> excessiveBodyTemperature() {
        return recordMapper.entitiesToModels(recordRepository.findAll())
                .stream()
                .filter(DailyTroubleshootRecordModel::isExceedTemp)
                .collect(groupingBy(DailyTroubleshootRecordModel::getPlot));
    }

    // 分页
    @Override
    public List<DailyTroubleshootRecordModel> pagQuery(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return recordMapper.entitiesToModels(recordRepository.findAll(pageable).getContent());
    }

    // 排查每个小区今日登记的人员
    @Override
    public Map<String, List<DailyTroubleshootRecordModel>> registerToda(Timestamp startTime, Timestamp endTime) {
        return recordMapper.entitiesToModels(
                recordRepository.todayRecord(startTime, endTime))
                .stream()
                .collect(groupingBy(DailyTroubleshootRecordModel::getPlot));
    }

    @Override
    public List<DiagnosisCountModel> DiagnosisCount() {
        List<DiagnosisCountModel> diagnosisCountModels = new ArrayList<>();
        List<DSourceDataEntity> dSourceDataEntities = dSourceDataRepository.queryByDataSourceIdOrderBySortAsc(commId);
        dSourceDataEntities.forEach(dSourceDataEntity -> {
            DiagnosisCountModel diagnosisCountModel = new DiagnosisCountModel();
            diagnosisCountModel.setdSourceDataModel(dSourceDataMapper.entityToModel(dSourceDataEntity));
            diagnosisCountModel.setCount(recordRepository.queryCountByDiagnosisSituation(dSourceDataEntity.getId()));
            diagnosisCountModels.add(diagnosisCountModel);
        });
        return diagnosisCountModels;
    }


//    @Override  //  DailyTroubleFilterModel dailyTroubleFilterModel
//    public List<DailyStatisticModel> queryByConditions() {
//        // 查所有小区
//        List<DailyStatisticModel> dailyStatisticModels = new ArrayList<>();
//        List<DSourceDataEntity> allByDataSource = dSourceDataRepository.findAllByDataSourceId(commId);
//
//        for (int i = 0; i < allByDataSource.size(); i++) {
//            DSourceDataEntity dataSourceEntity = allByDataSource.get(i);
//
//            // 根据小区查所有楼栋
//            List<DailyTroubleshootRecordEntity> recordEntityList = recordRepository.queryAllByPlot(dataSourceEntity.getId());
//            for (int j = 0; j < recordEntityList.size(); j++) {
//                DailyTroubleshootRecordEntity recordEntity = recordEntityList.get(j);
//                List<DailyTroubleshootRecordEntity> recordEntities = recordRepository.queryAllByPlotAndbAndBuilding(dataSourceEntity.getId(), recordEntity.getBuilding());
//                for (int k = 0; k < recordEntities.size(); k++) {
//                    DailyTroubleshootRecordEntity unit = recordEntities.get(k);
//                    DailyStatisticModel dailyStatisticModel = new DailyStatisticModel();
//                    dailyStatisticModel.setPlotId(dataSourceEntity.getId());
//                    dailyStatisticModel.setPlotName(dataSourceEntity.getName());
//                    dailyStatisticModel.setBuilding(recordEntity.getBuilding());
//                    dailyStatisticModel.setUnitNumber(unit.getUnitNumber());
//
//                    dailyStatisticModels.add(dailyStatisticModel);
//                }
//            }
//        }
//        Set<DailyStatisticModel> set = new TreeSet<>(new Comparator<DailyStatisticModel>() {
//            @Override
//            public int compare(DailyStatisticModel t1, DailyStatisticModel t2) {
//                int count = 1;
//                if (StringUtils.equals(t1.getPlotId(), t2.getPlotId()) &&
//                        StringUtils.equals(t1.getBuilding(), t2.getBuilding())
//                        && StringUtils.equals(t1.getUnitNumber(), t2.getUnitNumber())) {
//                    count = 0;
//                }
//                return count;
//            }
//        });
//        set.addAll(dailyStatisticModels);
//        List<DailyStatisticModel> result = new ArrayList<>(set);
//
//        for (int t = 0; t < result.size(); t++) {
//            DailyStatisticModel dailyStatisticModel = result.get(t);
//
//            Integer integer = recordRepository.todayRecordCon(STARTTIME, ENDTIME, dailyStatisticModel.getPlotId(), dailyStatisticModel.getBuilding(), dailyStatisticModel.getUnitNumber());
//            dailyStatisticModel.setChecked(integer);
//
//            // 统计未排查的人数 统计有误
//            List<DailyTroubleshootRecordEntity> allPersons = recordRepository.queryUnchecked(dailyStatisticModel.getPlotId(), dailyStatisticModel.getBuilding(), dailyStatisticModel.getUnitNumber());
//            List<DailyTroubleshootRecordEntity> recordEntities = recordRepository.queryAllByPlotAndBuildingAndUnitNumber(dailyStatisticModel.getPlotId(), dailyStatisticModel.getBuilding(), dailyStatisticModel.getUnitNumber(), STARTTIME, ENDTIME);
//
//            List<DailyTroubleshootRecordEntity> temp = allPersons.stream()
//                    .filter(item -> !recordEntities.stream()
//                            .map(e -> e.getName() + e.getPhone())
//                            .collect(toList())
//                            .contains(item.getName() + item.getPhone())).collect(toList());
//
//
//            Set<DailyStatisticModel> set2 = new TreeSet<>(new Comparator<DailyStatisticModel>() {
//                @Override
//                public int compare(DailyStatisticModel t1, DailyStatisticModel t2) {
//                    int count = 1;
//                    if (StringUtils.equals(t1.getPlotId(), t2.getPlotId()) &&
//                            StringUtils.equals(t1.getBuilding(), t2.getBuilding())
//                            && StringUtils.equals(t1.getUnitNumber(), t2.getUnitNumber())) {
//                        count = 0;
//                    }
//                    return count;
//                }
//            });
//            set.addAll(dailyStatisticModels);
//            dailyStatisticModel.setUnchecked(new ArrayList<>(temp).size());
//        }
//        return result;
//    }



    // 重写 分组查询统计


    @Override
    public List<DailyStatisticModel> queryByConditions() {
        List<DailyStatisticModel> dailyStatisticModels = new ArrayList<>();
        // 根据plot、build、unit去重
        List<DailyTroubleshootRecordEntity> recordEntities = recordRepository.queryDistPlotBuildUnit();
        for (int i = 0 ; i < recordEntities.size() ; i++){
            DailyStatisticModel dailyStatisticModel = new DailyStatisticModel();
            DailyTroubleshootRecordEntity recordEntity = recordEntities.get(i);
            String plot = recordEntity.getPlot();
            String building = recordEntity.getBuilding();
            String unit = recordEntity.getUnitNumber();
            dailyStatisticModel.setPlotId(plot);
            dailyStatisticModel.setBuilding(building);
            dailyStatisticModel.setUnitNumber(unit);
            // 小区名称
            if(!"".equals(plot) && plot != null){
                DSourceDataEntity dSourceDataEntity = dSourceDataRepository.getOne(plot);
                if(dSourceDataEntity != null && dSourceDataEntity.getName() != null)
                    dailyStatisticModel.setPlotName(dSourceDataEntity.getName());
            }
            // 今日已排查
            List<DailyTroubleshootRecordEntity> alreadyChecked = recordRepository.queryTodayChecked(plot, building, unit, STARTTIME, ENDTIME);
            int checked = alreadyChecked.size();
            dailyStatisticModel.setChecked(checked);
            int fever = (int) alreadyChecked.stream().filter(DailyTroubleshootRecordEntity::isExceedTemp).count() ;
            dailyStatisticModel.setFeverCount(fever);
            // 小区人数总和
            List<DailyTroubleshootRecordEntity> allPerson = recordRepository.queryPersonGroup(plot, building, unit);
            // 今日未排查
            dailyStatisticModel.setUnchecked(allPerson.size() - checked);
            dailyStatisticModels.add(dailyStatisticModel);
        }
        return  dailyStatisticModels;

    }

    @Override
    public List<DailyTroubleshootRecordModel> queryByPlotAndBuild(DailyTroubleFilterModel dailyTroubleFilterModel) {
        String plot = dailyTroubleFilterModel.getDailyStatisticModel().getPlotId();
        String building = dailyTroubleFilterModel.getDailyStatisticModel().getBuilding();
        String unitNumber = dailyTroubleFilterModel.getDailyStatisticModel().getUnitNumber();
        List<DailyTroubleshootRecordEntity> recordEntities = recordRepository.queryAllByPlotAndBuildingAndUnitNumber(plot, building, unitNumber, STARTTIME, ENDTIME);
        // 暂不处理 小区
        // dailyTroubleFilterModel.getPlots()
        // 是否发热
        if (dailyTroubleFilterModel.getIsFaver().size() != 2 && dailyTroubleFilterModel.getIsFaver().size() != 0) {
            recordEntities = recordEntities.stream().filter(a -> a.isExceedTemp() == dailyTroubleFilterModel.getIsFaver().get(0)).collect(Collectors.toList());
        }
        // plots
        if (dailyTroubleFilterModel.getPlots().size() > 0) {
            recordEntities = recordEntities.stream().filter(a -> dailyTroubleFilterModel.getPlots().contains(a.getPlot())).collect(Collectors.toList());
        }
        //  medicalOpinion
        List<DailyTroubleshootRecordEntity> opions = new ArrayList<>();
        if (dailyTroubleFilterModel.getMedicalOpinion().size() > 0) {
//            for(int i = 0 ; i < dailyTroubleFilterModel.getMedicalOpinion().size() ; i++){
//                medicalOpinion += dailyTroubleFilterModel.getMedicalOpinion().get(i);
//            }
            String medicalOpinion = dailyTroubleFilterModel.getMedicalOpinion().stream().collect(Collectors.joining());
            for (int t = 0; t < recordEntities.size(); t++) {
                if (recordEntities.get(t).getMedicalOpinion().contains(medicalOpinion)) {
                    opions.add(recordEntities.get(t));
                }
            }
        } else {
            opions = recordEntities;
        }

        // 关键字
        List<DailyTroubleshootRecordEntity> troubleshootRecordEntities = new ArrayList<>();
        if (!"".equals(dailyTroubleFilterModel.getKeyWord())) {
            String keyWord = dailyTroubleFilterModel.getKeyWord();
            for (int i = 0; i < opions.size(); i++) {
                if (opions.get(i).getName().contains(keyWord)
                        || opions.get(i).getPhone().contains(keyWord)
                        || opions.get(i).getAddress().contains(keyWord)) {
                    troubleshootRecordEntities.add(opions.get(i));
                }
            }
        } else {
            troubleshootRecordEntities = opions;
        }
        // 分页
        long skip = dailyTroubleFilterModel.getPage() * dailyTroubleFilterModel.getPageSize();
        List<DailyTroubleshootRecordEntity> collect = troubleshootRecordEntities.stream().skip(skip).limit(Long.valueOf(dailyTroubleFilterModel.getPageSize())).collect(Collectors.toList());
        return recordMapper.entitiesToModels(collect);
    }

    //    // 往基础数据表添加数据
    public void addToBasicInformation(DailyTroubleshootRecordEntity recordEntity) {
        List<BasicInformationEntity> allByNameAndPhone = basicInformationRepository.findAllByNameAndPhone(recordEntity.getName(), recordEntity.getPhone());
        BasicInformationModel basicInformationModel = new BasicInformationModel();
        basicInformationModel.setId(UUID.randomUUID().toString());
        basicInformationModel.setCode(UUID.randomUUID().toString());
        basicInformationModel.setAddress(recordEntity.getAddress());
        basicInformationModel.setIdentificationNumber(recordEntity.getIdentificationNumber());
        basicInformationModel.setName(recordEntity.getName());
        basicInformationModel.setPhone(recordEntity.getPhone());
        basicInformationModel.setSex(recordEntity.getSex());
        if (allByNameAndPhone.isEmpty()) {
            basicInformationService.addPerson(basicInformationModel);
        } else {
            basicInformationService.updatePerson(basicInformationModel);
        }
    }


    // 该小区所有人（查plot、） - 该小区所有今日已排查 = 该小区未排查
    @Override
    public DailyStatisticPageModel queryPendingInvestigation(PendingInvestigatModel pendModel) {

        DailyStatisticPageModel dailyStatisticPageModel = new DailyStatisticPageModel();
        List<DailyTroubleshootRecordEntity> alreadyPends =
                recordRepository.queryAllByPlotAndBuildingAndUnitNumber
                        (pendModel.getPlot(), pendModel.getBuilding(), pendModel.getUnitNumber()
                                , STARTTIME, ENDTIME);
        List<DailyTroubleshootRecordEntity> allPerson = recordRepository.queryUnchecked(pendModel.getPlot(), pendModel.getBuilding(), pendModel.getUnitNumber());
        if (alreadyPends.isEmpty()) {
            dailyStatisticPageModel.setTotal(allPerson.size());
            dailyStatisticPageModel.setDailyTroubleshootRecordModels(recordMapper.entitiesToModels(allPerson));
            return dailyStatisticPageModel;
        }
        List<DailyTroubleshootRecordEntity> result = allPerson.stream()
                .filter(item -> !alreadyPends.stream()
                        .map(e -> e.getName() + e.getPhone())
                        .collect(toList())
                        .contains(item.getName() + item.getPhone())).collect(toList());
        long skip = pendModel.getPage() * pendModel.getPageSize();
        int total = result.size();
        List<DailyTroubleshootRecordEntity> collect = result.stream().skip(skip).limit(Long.valueOf(pendModel.getPageSize())).collect(toList());
        List<DailyTroubleshootRecordModel> dailyTroubleshootRecordModels = recordMapper.entitiesToModels(collect);

        dailyStatisticPageModel.setTotal(total);
        dailyStatisticPageModel.setDailyTroubleshootRecordModels(dailyTroubleshootRecordModels);

        return dailyStatisticPageModel;
    }
}

package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.common.util.DateUtil;
import com.gsafety.dawn.community.common.util.ExcelUtil;
import com.gsafety.dawn.community.manage.contract.model.DailyTroubleshootRecordModel;
import com.gsafety.dawn.community.manage.contract.model.DiagnosisCountModel;
import com.gsafety.dawn.community.manage.contract.service.DailyTroubleshootRecordService;
import com.gsafety.dawn.community.manage.service.datamappers.DSourceDataMapper;
import com.gsafety.dawn.community.manage.service.datamappers.DailyTroubleshootRecordMapper;
import com.gsafety.dawn.community.manage.service.entity.DSourceDataEntity;
import com.gsafety.dawn.community.manage.service.entity.DailyTroubleshootRecordEntity;
import com.gsafety.dawn.community.manage.service.entity.EpidemicPersonEntity;
import com.gsafety.dawn.community.manage.service.repository.DSourceDataRepository;
import com.gsafety.dawn.community.manage.service.repository.DailyTroubleshootRecordRepository;
import com.gsafety.dawn.community.manage.service.repository.EpidemicPersonRepository;
import org.apache.commons.net.ntp.TimeStamp;
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

import static java.util.stream.Collectors.groupingBy;

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
    private static final String commId = "a2e01f0e-6c86-4a41-bcf3-c07c1ffa2f82";

    @Autowired
    private DSourceDataRepository dSourceDataRepository;

    @Autowired
    private DSourceDataMapper dSourceDataMapper;

    // 日志
    private static Logger logger = LoggerFactory.getLogger(DailyTroubleshootRecordServiceImpl.class);

    // 时间
    private static final Timestamp TS = DateUtil.convertNowDate();

    // 一天开始时间
    private static final Timestamp STARTTIME = DateUtil.getStartTime();

    // 一天结束时间
    private static final Timestamp ENDTIME = DateUtil.getEndTime();


    @Override
    public DailyTroubleshootRecordModel addDailyRecord(DailyTroubleshootRecordModel dailyTroubleshootRecordModel) {

        // 今日记录
        List<DailyTroubleshootRecordEntity> recordEntities = recordRepository.todayRecord(STARTTIME, ENDTIME);
        List<DailyTroubleshootRecordEntity> collect = recordEntities.stream()
                .filter(a ->
                        a.getName().equals(dailyTroubleshootRecordModel.getName()) &&
                        a.getPhone().equals(dailyTroubleshootRecordModel.getPhone()))
                .collect(Collectors.toList());
        if(!collect.isEmpty())
            return null;

        recordRepository.queryAllByNameAndPhone(dailyTroubleshootRecordModel.getName(), dailyTroubleshootRecordModel.getPhone());
        DailyTroubleshootRecordEntity recordEntity = recordMapper.modelToEntity(dailyTroubleshootRecordModel);
        recordEntity.setId(UUID.randomUUID().toString());
        recordEntity.setCode(UUID.randomUUID().toString());
        recordEntity.setCreateTime(DateUtil.convertNowDate());
        recordEntity.setMultiTenancy(multiTenancy);
        DailyTroubleshootRecordEntity result = recordRepository.save(recordEntity);

        addEpidMic(result);

        return recordMapper.entityToModel(result);
    }

    @Override
    public DailyTroubleshootRecordModel updateDailyRecord(DailyTroubleshootRecordModel dailyTroubleshootRecordModel) {
        boolean exists = recordRepository.existsById(dailyTroubleshootRecordModel.getId());
        if (exists) {
            DailyTroubleshootRecordEntity recordEntity = recordRepository.saveAndFlush(recordMapper.modelToEntity(dailyTroubleshootRecordModel));
            addEpidMic(recordEntity);
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
        List<DailyTroubleshootRecordEntity> result = recordRepository.saveAll(recordEntities);
        result.forEach(a -> addEpidMic(a));
        return recordMapper.entitiesToModels(result);
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

            String plot = ExcelUtil.convertCellValueToString(row.getCell(6));

            List<DSourceDataEntity> dataList = dSourceDataRepository.findAllByName(plot);
            if(dataList.isEmpty())
                continue;

            plot = dataList.get(0).getId();

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
            String other = ExcelUtil.convertCellValueToString(row.getCell(12));
            String opinion = ExcelUtil.convertCellValueToString(row.getCell(13));
            String note = ExcelUtil.convertCellValueToString(row.getCell(14));
            if ("".equals(name) || "".equals(phone) || "".equals(address))
                continue;
            DailyTroubleshootRecordEntity recordEntity = new DailyTroubleshootRecordEntity();
            recordEntity.setId(UUID.randomUUID().toString());
            recordEntity.setCreateTime(TS);
            recordEntity.setMultiTenancy(multiTenancy);
            recordEntity.setAddress(address);

            recordEntity.setOtherSymptoms(other);
            recordEntity.setMedicalOpinion(opinion);
            recordEntity.setNote(note);

            if(!"".equals(age) && age != null){
                recordEntity.setAge(Integer.valueOf(age));
            }


            if(contact.equals("t")){
                recordEntity.setContact(true);
            } else {
                recordEntity.setContact(false);
            }

            if(tempture.equals("t")){
                recordEntity.setExceedTemp(true);
            } else {
                recordEntity.setExceedTemp(false);
            }

            recordEntity.setBuilding(build);
            recordEntity.setPlot(plot);
            recordEntity.setCode(UUID.randomUUID().toString());
            recordEntity.setIdentificationNumber(idCard);
            recordEntity.setSex(sex);
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
            epidemicPersonEntity.setMultiTenancy(multiTenancy);
            epidemicPersonEntity.setName(dailyTroubleshootRecordEntity.getName());
            epidemicPersonEntity.setMobileNumber(dailyTroubleshootRecordEntity.getPhone());

            epidemicPersonRepository.save(epidemicPersonEntity);
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
        Pageable pageable = PageRequest.of(page , pageSize);
        return recordMapper.entitiesToModels(recordRepository.findAll(pageable).getContent());
    }

    // 排查每个小区今日登记的人员
    @Override
    public Map<String, List<DailyTroubleshootRecordModel>> registerToda(Timestamp startTime , Timestamp endTime) {
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
            DiagnosisCountModel diagnosisCountModel=new DiagnosisCountModel();
            diagnosisCountModel.setdSourceDataModel(dSourceDataMapper.entityToModel(dSourceDataEntity));
            diagnosisCountModel.setCount(recordRepository.queryCountByDiagnosisSituation(dSourceDataEntity.getId()));
            diagnosisCountModels.add(diagnosisCountModel);
        });
        return diagnosisCountModels;
    }
}

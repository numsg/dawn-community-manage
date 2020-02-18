package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.common.util.DateUtil;
import com.gsafety.dawn.community.manage.contract.model.ModifyMedicalTreatmentModel;
import com.gsafety.dawn.community.manage.contract.model.refactor.PersonBase;
import com.gsafety.dawn.community.manage.contract.model.refactor.TroubleshootRecord;
import com.gsafety.dawn.community.manage.contract.model.total.*;
import com.gsafety.dawn.community.manage.contract.model.EpidemicPersonModel;
import com.gsafety.dawn.community.manage.contract.service.EpidemicPersonService;
import com.gsafety.dawn.community.manage.service.datamappers.DSourceDataMapper;
import com.gsafety.dawn.community.manage.service.datamappers.EpidemicPersonMapper;
import com.gsafety.dawn.community.manage.service.entity.DSourceDataEntity;
import com.gsafety.dawn.community.manage.service.entity.DataSourceEntity;
import com.gsafety.dawn.community.manage.service.entity.EpidemicPersonEntity;
import com.gsafety.dawn.community.manage.service.entity.refactor.TroubleshootHistoryRecordEntity;
import com.gsafety.dawn.community.manage.service.repository.DSourceDataRepository;
import com.gsafety.dawn.community.manage.service.repository.DataSourceRepository;
import com.gsafety.dawn.community.manage.service.repository.EpidemicPersonRepository;
import com.gsafety.dawn.community.manage.service.repository.refactor.PersonBaseRepository;
import com.gsafety.dawn.community.manage.service.repository.refactor.TroubleshootHistoryRecordRepository;
import com.gsafety.dawn.community.manage.service.serviceimpl.share.DataSourceShareIds;
import fr.opensagres.xdocreport.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

@Service
@Transactional
public class EpidemicPersonServiceImpl extends DataSourceShareIds implements EpidemicPersonService {
    @Autowired
    private EpidemicPersonMapper epidemicPersonMapper;
    @Autowired
    private EpidemicPersonRepository epidemicPersonRepository;
    @Autowired
    private DSourceDataRepository dSourceDataRepository;
    @Autowired
    private DSourceDataMapper dSourceDataMapper;
    @Autowired
    private DataSourceRepository dataSourceRepository;
    @Autowired
    private TroubleshootHistoryRecordRepository troubleshootHistoryRecordRepository;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    // 诊断情况id
    private static final String diagnosisId = "97629e08-cb68-489d-8f62-8c8467358d69";
    // 诊断为确认
    private static final String diagnosisConfirmedId = "c9eedfbc-ae5a-40b7-8a62-c049c5678deb";
    // 诊断为疑似
    private static final String diagnosisSuspectId = "6293737c-5775-426d-9845-f919eafba1be";
    //诊断为治愈
    private static final String diagnosisHealthId = "2309505a-5d1e-4e04-a51b-d3c033ad1f5c";
    // 诊断为死亡
    private static final String diagnosisDeathId = "ed8c449a-0d2b-40fd-88b8-da04cd0193f9";

    @Override
    public EpidemicPersonModel addOneEpidemicPerson(EpidemicPersonModel epidemicPersonModel) {
        List<EpidemicPersonEntity> epidemicPersonEntities = epidemicPersonRepository.queryAllByNameAndMobileNumber(epidemicPersonModel.getName(), epidemicPersonModel.getMobileNumber());
        EpidemicPersonModel result = null;
        if (CollectionUtils.isEmpty(epidemicPersonEntities)) {
            logger.info("列表中无该重点关注人员，新增重点关注人员");
            epidemicPersonModel.setUpdateTime(new Date());
            epidemicPersonModel.setSubmitTime(new Date());
            EpidemicPersonEntity epidemicPersonEntity = epidemicPersonMapper.modelToEntity(epidemicPersonModel);
            result = epidemicPersonMapper.entityToModel(epidemicPersonRepository.save(epidemicPersonEntity));
        } else {
            logger.info("列表中存在该重点关注人员，修改数据");
            result = modifyOneEpidemicPerson(epidemicPersonModel.getId(), epidemicPersonModel);
        }
        return result;
    }

    @Override
    public EpidemicPersonModel modifyOneEpidemicPerson(String id, EpidemicPersonModel epidemicPersonModel) {
        if (StringUtils.isEmpty(epidemicPersonModel.getId()) || !epidemicPersonRepository.existsById(id)) {
            logger.error("修改失败，重点关注人员不存在");
            return null;
        }
        epidemicPersonModel.setUpdateTime(new Date());
        EpidemicPersonEntity epidemicPersonEntity = epidemicPersonRepository.saveAndFlush(epidemicPersonMapper.modelToEntity(epidemicPersonModel));
        return epidemicPersonMapper.entityToModel(epidemicPersonEntity);
        //        if (id == null || !id.equals(epidemicPersonModel.getId()) || !epidemicPersonRepository.existsById(id)) {
        //            logger.error("修改失败，重点关注人员不存在");
        //            return null;
        //        }
        //        EpidemicPersonModel result = new EpidemicPersonModel();
        //        if (0 < epidemicPersonRepository.updateEpidemicPerson(epidemicPersonModel.getId(), epidemicPersonModel.getName(),
        //                epidemicPersonModel.getGender(), epidemicPersonModel.getAge(), epidemicPersonModel.getVillageId(), epidemicPersonModel.getTemperature(),
        //                epidemicPersonModel.getDiagnosisSituation(), epidemicPersonModel.getMedicalCondition(), epidemicPersonModel.getSpecialSituation(),
        //                epidemicPersonModel.getSubmitTime(), epidemicPersonModel.getDiseaseTime(), epidemicPersonModel.getUpdateTime(), epidemicPersonModel.getNote(),
        //                epidemicPersonModel.getMultiTenancy(), epidemicPersonModel.getExpendProperty(), epidemicPersonModel.getMobileNumber())) {
        //            result = epidemicPersonMapper.entityToModel(epidemicPersonRepository.getOne(id));
        //        }
        //        return result;
    }

    @Override
    public Boolean deleteOneEpidemicPerson(String id) {
        Boolean result = false;
        if (epidemicPersonRepository.existsById(id)) {
            epidemicPersonRepository.deleteById(id);
            result = true;
        }
        return result;
    }

    @Override
    public List<DiagnosisCountModel> diagnosisCount(String districtCode) {
        List<DiagnosisCountModel> result = new ArrayList<>();
        List<DSourceDataEntity> dSourceDataEntities = dSourceDataRepository.queryByDataSourceIdOrderBySortAsc(diagnosisId);
        dSourceDataEntities.forEach(dSourceDataEntity -> {
            DiagnosisCountModel diagnosisCountModel = new DiagnosisCountModel();
            diagnosisCountModel.setdSourceDataModel(dSourceDataMapper.entityToModel(dSourceDataEntity));
            diagnosisCountModel.setCount(epidemicPersonRepository.queryCountByDiagnosisSituationAndMultiTenancy(dSourceDataEntity.getId(), districtCode));
            result.add(diagnosisCountModel);
        });
        return result;
    }

    @Override
    public List<SpecialCountModel> diagnosisCountWithConfirmedAndSuspected(String communityId) {
        return null;
    }

    @Override
    public List<SpecialCountModel> diagnosisCountWithHealthAndDeath(String communityId) {


        // 治愈
        List<EpidemicPersonEntity> queryResult = epidemicPersonRepository.queryAllByDiagnosisSituationAndMultiTenancyOrderByUpdateTimeAsc(diagnosisHealthId, "123456");
        queryResult.forEach(date -> {
            System.out.println(date.getUpdateTime());
        });
        return null;
    }


    // 更新医疗情况信息
    @Override
    public boolean modifyMedicalTreatment(ModifyMedicalTreatmentModel medicalTreatmentModel) {
        boolean flag = false;
        boolean exists = epidemicPersonRepository.existsById(medicalTreatmentModel.getId());
        if (exists) {
            Integer integer = epidemicPersonRepository.updateMedical(medicalTreatmentModel.getId(), medicalTreatmentModel.getMedicalTreatmentId(), medicalTreatmentModel.getOperator(), new Date());
            if (integer > 0)
                flag = true;
        }
        return flag;
    }

    // 统计-总体-分类诊疗意见
    @Override
    public EpidemicTotalStatisticModel overallClassification(String districtCode, String dataSourceId) {
        return totalClaOrMedical(districtCode, dataSourceId, true);
    }

    // 统计-总体-医疗情况
    @Override
    public EpidemicTotalStatisticModel overallMedical(String districtCode, String dataSourceId) {
        return totalClaOrMedical(districtCode, dataSourceId, false);
    }

    // 统计-小区-分类诊疗意见
    @Override
    public List<EpidemicTotalStatisticModel> plotClassification(String districtCode, String dataSourceId) {
        return plotClaOrMedical(districtCode, dataSourceId, true);
    }

    // 统计-小区-医疗情况
    @Override
    public List<EpidemicTotalStatisticModel> plotMedical(String districtCode, String dataSourceId) {
        return plotClaOrMedical(districtCode, dataSourceId, false);
    }

    // 总体统计按医疗分类意见和医疗情况
    public EpidemicTotalStatisticModel totalClaOrMedical(String districtCode, String dataSourceId, boolean classOrMedical) {
        if (StringUtils.isEmpty(districtCode) || StringUtils.isEmpty(dataSourceId))
            return null;
        // 这里先不要tag
        List<DataSourceEntity> dataSourceEntities = dataSourceRepository.queryAllByDescription(districtCode);
        // 查不到社区返回空
        if (CollectionUtils.isEmpty(dataSourceEntities))
            return null;
        DataSourceEntity dataSourceEntity = dataSourceEntities.get(0);
        EpidemicTotalStatisticModel epidemicTotalStatisticModel = new EpidemicTotalStatisticModel();
        epidemicTotalStatisticModel.setName(dataSourceEntity.getName());
        epidemicTotalStatisticModel.setId(dataSourceEntity.getId());
        List<EpidemicTotalNodeModel> epidemicTotalNodeModels = new ArrayList<>();
        // List<DiagnosisCountModel> diagnosisCountModels = new ArrayList<>();
        List<DSourceDataEntity> dSourceDataEntities = dSourceDataRepository.queryByDataSourceIdOrderBySortAsc(dataSourceId);
        dSourceDataEntities.forEach(a -> {
            // DiagnosisCountModel diagnosisCountModel = new DiagnosisCountModel();
            EpidemicTotalNodeModel epidemicTotalNodeModel = new EpidemicTotalNodeModel();
            epidemicTotalNodeModel.setTypeName(a.getName());
            epidemicTotalNodeModel.setColor(a.getImgColor());
            epidemicTotalNodeModel.setId(a.getId());
            // diagnosisCountModel.setdSourceDataModel(dSourceDataMapper.entityToModel(a));
            Integer integer = 0;
            if (classOrMedical) {
                integer = epidemicPersonRepository.countAllByMultiTenancyAndConfirmedDiagnosis(districtCode, a.getId());
            } else {
                integer = epidemicPersonRepository.countAllByMultiTenancyAndMedicalCondition(districtCode, a.getId());
            }
            epidemicTotalNodeModel.setCount(integer);
            epidemicTotalNodeModels.add(epidemicTotalNodeModel);
            // diagnosisCountModel.setCount(integer);
            // diagnosisCountModels.add(diagnosisCountModel);
        });
        Integer count = epidemicTotalNodeModels.stream().map(EpidemicTotalNodeModel::getCount).reduce(0, Integer::sum);
        epidemicTotalStatisticModel.setCount(count);
        epidemicTotalStatisticModel.setNodeModels(epidemicTotalNodeModels);
        return epidemicTotalStatisticModel;
    }


    // 总体统计按医疗分类意见和医疗情况
    public List<EpidemicTotalNodeModel> totalClaOrMedical(String districtCode, String dataSourceId, String plotId, boolean classOrMedical) {
        if (StringUtils.isEmpty(districtCode) || StringUtils.isEmpty(dataSourceId))
            return Collections.emptyList();
        // List<DiagnosisCountModel> diagnosisCountModels = new ArrayList<>();
        List<DSourceDataEntity> dSourceDataEntities = dSourceDataRepository.queryByDataSourceIdOrderBySortAsc(dataSourceId);
        List<EpidemicTotalNodeModel> epidemicTotalNodeModels = new ArrayList<>();
        dSourceDataEntities.forEach(a -> {
            EpidemicTotalNodeModel epidemicTotalNodeModel = new EpidemicTotalNodeModel();
            epidemicTotalNodeModel.setColor(a.getImgColor());
            epidemicTotalNodeModel.setId(a.getId());
            epidemicTotalNodeModel.setTypeName(a.getName());
            // DiagnosisCountModel diagnosisCountModel = new DiagnosisCountModel();
            // diagnosisCountModel.setdSourceDataModel(dSourceDataMapper.entityToModel(a));
            Integer integer = 0;
            if (classOrMedical) {
                integer = epidemicPersonRepository.countAllByMultiTenancyAndVillageIdAndConfirmedDiagnosis(districtCode, plotId, a.getId());
            } else {
                integer = epidemicPersonRepository.countAllByMultiTenancyAndVillageIdAndMedicalCondition(districtCode, plotId, a.getId());
            }
            epidemicTotalNodeModel.setCount(integer);
            epidemicTotalNodeModels.add(epidemicTotalNodeModel);
            // diagnosisCountModel.setCount(integer);
            // diagnosisCountModels.add(diagnosisCountModel);
        });
        return epidemicTotalNodeModels;
    }

    // 小区统计分类诊疗意见和医疗情况
    public List<EpidemicTotalStatisticModel> plotClaOrMedical(String districtCode, String dataSourceId, boolean classOrMedical) {
        if (StringUtils.isEmpty(districtCode) || StringUtils.isEmpty(dataSourceId))
            return Collections.emptyList();
        // 这里先不要tag
        List<DataSourceEntity> dataSourceEntities = dataSourceRepository.queryAllByDescription(districtCode);
        // 查不到社区返回空
        if (CollectionUtils.isEmpty(dataSourceEntities))
            return Collections.emptyList();
        DataSourceEntity dataSourceEntity = dataSourceEntities.get(0);
        List<DSourceDataEntity> dataSourcePlots = dSourceDataRepository.findAllByDataSourceId(dataSourceEntity.getId());
        // 社区没有小区返回空
        if (CollectionUtils.isEmpty(dataSourcePlots))
            return Collections.emptyList();
        List<EpidemicTotalStatisticModel> epidemicTotalStatisticModels = new ArrayList<>();
        // List<DistrictDiagnosisCountModel> districtDiagnosisCountModels = new ArrayList<>();
        dataSourcePlots.forEach(a -> {
            // DistrictDiagnosisCountModel districtDiagnosisCountModel = new DistrictDiagnosisCountModel();
            // districtDiagnosisCountModel.setPlotId(a.getId());
            // districtDiagnosisCountModel.setPlotName(a.getName());
            List<EpidemicTotalNodeModel> epidemicTotalNodeModels = totalClaOrMedical(districtCode, dataSourceId, a.getId(), classOrMedical);
            // districtDiagnosisCountModel.setDiagnosisCountModels(diagnosisCountModels);
            // districtDiagnosisCountModels.add(districtDiagnosisCountModel);
            EpidemicTotalStatisticModel epidemicTotalStatisticModel = new EpidemicTotalStatisticModel();
            epidemicTotalStatisticModel.setId(a.getId());
            epidemicTotalStatisticModel.setName(a.getName());
            epidemicTotalStatisticModel.setNodeModels(epidemicTotalNodeModels);
            Integer count = epidemicTotalNodeModels.stream().map(EpidemicTotalNodeModel::getCount).reduce(0, Integer::sum);
            epidemicTotalStatisticModel.setCount(count);
            epidemicTotalStatisticModels.add(epidemicTotalStatisticModel);
            // epidemicTotalStatisticModel.set
        });
        // epidemicTotalStatisticModels.stream().sorted(Comparator.comparing(a -> a.getNodeModels().stream().sorted(Comparator.comparing(EpidemicTotalNodeModel::getCount))))
//        Collections.sort(epidemicTotalStatisticModels, new CompareConfirmed());
        List<EpidemicTotalStatisticModel> collect = epidemicTotalStatisticModels.stream()
                .sorted(comparing(EpidemicTotalStatisticModel::getCount).reversed())
                .collect(Collectors.toList());
//        Collections.reverse(collect);
        return collect;
    }

    // 把日常排查的数据添加到重点关注人员
    @Override
    public boolean syncTroubleshooting(TroubleshootRecord troubleshootRecord) {
        logger.info("进入");
        if(StringUtils.isEmpty(troubleshootRecord.getPersonBaseId()))
            return false;
        logger.info("personbase");
//        boolean exist = personBaseRepository.existsById(troubleshootRecord.getId());
//        if(!exist)
//            return false;
        PersonBase personBase = troubleshootRecord.getPersonBase();
        boolean isDoubleFever = false;
        // 体温大于37.3查询该人员昨天的记录
        if(troubleshootRecord.getIsExceedTemp()){
            // 昨天开始与结束的时间
            Date lastDayStartDate = DateUtil.getLastDayStartDate();
            Date lastDayEndDate = DateUtil.getLastDayEndDate();
            List<TroubleshootHistoryRecordEntity> allByPersonBaseIdAndCreateTimeBetween = troubleshootHistoryRecordRepository.findAllByPersonBaseIdAndCreateTimeBetween(troubleshootRecord.getPersonBaseId(), lastDayStartDate, lastDayEndDate);
            // 昨天的记录不为空
            if(!CollectionUtils.isEmpty(allByPersonBaseIdAndCreateTimeBetween)
                    && allByPersonBaseIdAndCreateTimeBetween.get(0).getIsExceedTemp())
                isDoubleFever = true;
        }
        // 体温连续两天大于37.3，或者3类分类医疗意见
        if (     isDoubleFever ||
                !StringUtils.isEmpty(troubleshootRecord.getMedicalOpinion())
                        && (troubleshootRecord.getMedicalOpinion().equals(CONFIRMEDPATIENTID)
                        || troubleshootRecord.getMedicalOpinion().equals(SUSPECTEDPATIENTID)
                        || troubleshootRecord.getMedicalOpinion().equals(CTDIAGNOSISPNEUMONIAID))
        ) {
            // 组装成model
            EpidemicPersonModel epidemicPersonModel = new EpidemicPersonModel();
            // id
            epidemicPersonModel.setId(UUID.randomUUID().toString());
            // name
            epidemicPersonModel.setName(personBase.getName());
            // age
            epidemicPersonModel.setAge(troubleshootRecord.getAge());
            // gender
            epidemicPersonModel.setGender(personBase.getSex());
            // mobileNumber
            epidemicPersonModel.setMobileNumber(personBase.getPhone());
            // villageId
            epidemicPersonModel.setVillageId(troubleshootRecord.getPlot());
            // temperature
            epidemicPersonModel.setTemperature(troubleshootRecord.getIsExceedTemp());
            // diagnosisSituation
            // 确诊情况
            // medicalCondition
            epidemicPersonModel.setMedicalCondition(NOMEDICAL);
            // specialSituation
            // 特殊情况
            // note
            epidemicPersonModel.setNote(troubleshootRecord.getNote());
            // multiTenancy
            epidemicPersonModel.setMultiTenancy(troubleshootRecord.getMultiTenancy());
            // expendProperty
            epidemicPersonModel.setExpendProperty(troubleshootRecord.getExpendProperty());
            // isContact
            epidemicPersonModel.setContact(troubleshootRecord.getIsContact());
            // confirmedDiagnosis
            epidemicPersonModel.setConfirmedDiagnosis(troubleshootRecord.getMedicalOpinion());
            // reserveField
            //
            // isByphone
            epidemicPersonModel.setByphone(troubleshootRecord.getIsByPhone());
            // operator
            //
            // building
            epidemicPersonModel.setBuilding(troubleshootRecord.getBuilding());
            // unit_number
            epidemicPersonModel.setUnitNumber(troubleshootRecord.getUnitNumber());
            // roomID
            epidemicPersonModel.setRoomNumber(troubleshootRecord.getRoomNo());
            // 创建时间
            epidemicPersonModel.setSubmitTime(new Date());
            // 提交时间
            epidemicPersonModel.setUpdateTime(new Date());
            // diseaseTime 时间
            epidemicPersonModel.setDiseaseTime(new Date());
            // 保存
            EpidemicPersonModel result = addOneEpidemicPerson(epidemicPersonModel);
            if(result != null)
                return true;
        }
        return false;
    }

    // 体温是否小于37.3
    public boolean isFever(String tempture){
        String str = "('a:小于36℃', 'b:36-36.5℃', 'c:36.5-37℃' , 'd:37-37.3℃')";
        boolean contains = str.contains(tempture);
        if(contains)
            return false;
        return true;
    }

}

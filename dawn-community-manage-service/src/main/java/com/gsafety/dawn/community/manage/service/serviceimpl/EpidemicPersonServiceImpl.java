package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.manage.contract.model.ModifyMedicalTreatmentModel;
import com.gsafety.dawn.community.manage.contract.model.total.*;
import com.gsafety.dawn.community.manage.contract.model.EpidemicPersonModel;
import com.gsafety.dawn.community.manage.contract.service.EpidemicPersonService;
import com.gsafety.dawn.community.manage.service.datamappers.DSourceDataMapper;
import com.gsafety.dawn.community.manage.service.datamappers.DataSourceMapper;
import com.gsafety.dawn.community.manage.service.datamappers.EpidemicPersonMapper;
import com.gsafety.dawn.community.manage.service.entity.DSourceDataEntity;
import com.gsafety.dawn.community.manage.service.entity.DataSourceEntity;
import com.gsafety.dawn.community.manage.service.entity.EpidemicPersonEntity;
import com.gsafety.dawn.community.manage.service.repository.DSourceDataRepository;
import com.gsafety.dawn.community.manage.service.repository.DataSourceRepository;
import com.gsafety.dawn.community.manage.service.repository.EpidemicPersonRepository;
import fr.opensagres.xdocreport.core.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class EpidemicPersonServiceImpl implements EpidemicPersonService {
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
        if (!CollectionUtils.isEmpty(epidemicPersonEntities)) {
            logger.info("新增失败，重点关注人员名称和电话已存在");
            return null;
        }
        EpidemicPersonEntity epidemicPersonEntity = epidemicPersonMapper.modelToEntity(epidemicPersonModel);
        return epidemicPersonMapper.entityToModel(epidemicPersonRepository.save(epidemicPersonEntity));
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
        List<EpidemicTotalStatisticModel> collect = epidemicTotalStatisticModels.stream().sorted(Comparator.comparing(a -> a.getNodeModels().get(0).getCount())).collect(Collectors.toList());
        Collections.reverse(collect);
        return collect;
    }
}

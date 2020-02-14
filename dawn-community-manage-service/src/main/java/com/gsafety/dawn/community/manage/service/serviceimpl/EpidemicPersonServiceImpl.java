package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.manage.contract.model.RequestModel;
import com.gsafety.dawn.community.manage.contract.model.total.DiagnosisCountModel;
import com.gsafety.dawn.community.manage.contract.model.EpidemicPersonModel;
import com.gsafety.dawn.community.manage.contract.model.total.SpecialCountModel;
import com.gsafety.dawn.community.manage.contract.service.EpidemicPersonService;
import com.gsafety.dawn.community.manage.service.datamappers.DSourceDataMapper;
import com.gsafety.dawn.community.manage.service.datamappers.EpidemicPersonMapper;
import com.gsafety.dawn.community.manage.service.entity.DSourceDataEntity;
import com.gsafety.dawn.community.manage.service.entity.EpidemicPersonEntity;
import com.gsafety.dawn.community.manage.service.repository.DSourceDataRepository;
import com.gsafety.dawn.community.manage.service.repository.EpidemicPersonRepository;
import com.gsafety.java.common.utils.HttpClientUtil;
import com.gsafety.java.common.utils.HttpClientUtilImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    // 诊断情况id
    private static final String diagnosisId = "97629e08-cb68-489d-8f62-8c8467358d69";
    // 诊断为确认
    private static final String diagnosisConfirmedId="c9eedfbc-ae5a-40b7-8a62-c049c5678deb";
    // 诊断为疑似
    private static final String diagnosisSuspectId="6293737c-5775-426d-9845-f919eafba1be";
    //诊断为治愈
    private static final String diagnosisHealthId="2309505a-5d1e-4e04-a51b-d3c033ad1f5c";
    // 诊断为死亡
    private static final String diagnosisDeathId="ed8c449a-0d2b-40fd-88b8-da04cd0193f9";

    @Override
    public EpidemicPersonModel addOneEpidemicPerson(EpidemicPersonModel epidemicPersonModel) {
        EpidemicPersonEntity epidemicPersonEntity = epidemicPersonMapper.modelToEntity(epidemicPersonModel);
        return epidemicPersonMapper.entityToModel(epidemicPersonRepository.save(epidemicPersonEntity));
    }

    @Override
    public EpidemicPersonModel modifyOneEpidemicPerson(String id, EpidemicPersonModel epidemicPersonModel) {
        EpidemicPersonModel result = new EpidemicPersonModel();
        if (id == null || !id.equals(epidemicPersonModel.getId()) || !epidemicPersonRepository.existsById(id)) {
            return result;
        }
        if (0 < epidemicPersonRepository.updateEpidemicPerson(epidemicPersonModel.getId(), epidemicPersonModel.getName(),
                epidemicPersonModel.getGender(), epidemicPersonModel.getAge(), epidemicPersonModel.getVillageId(), epidemicPersonModel.getTemperature(),
                epidemicPersonModel.getDiagnosisSituation(), epidemicPersonModel.getMedicalCondition(), epidemicPersonModel.getSpecialSituation(),
                epidemicPersonModel.getSubmitTime(), epidemicPersonModel.getDiseaseTime(), epidemicPersonModel.getUpdateTime(), epidemicPersonModel.getNote(),
                epidemicPersonModel.getMultiTenancy(), epidemicPersonModel.getExpendProperty(), epidemicPersonModel.getMobileNumber())) {
            result = epidemicPersonMapper.entityToModel(epidemicPersonRepository.getOne(id));
        }

        return result;
    }

    @Override
    public Boolean deleteOneEpidemicPerson(String id) {
        Boolean result = false;
        if (epidemicPersonRepository.existsById(id)) {
            epidemicPersonRepository.deleteById(id);
            result=true;
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
            diagnosisCountModel.setCount(epidemicPersonRepository.queryCountByDiagnosisSituationAndMultiTenancy(dSourceDataEntity.getId(),districtCode));
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
       List<EpidemicPersonEntity> queryResult=epidemicPersonRepository.queryAllByDiagnosisSituationAndMultiTenancyOrderByUpdateTimeAsc(diagnosisHealthId,"123456");
        queryResult.forEach(date->{
            System.out.println(date.getUpdateTime());
        });
        return null;
    }
}

package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.manage.contract.model.DiagnosisCountModel;
import com.gsafety.dawn.community.manage.contract.model.EpidemicPersonModel;
import com.gsafety.dawn.community.manage.contract.service.EpidemicPersonService;
import com.gsafety.dawn.community.manage.service.datamappers.DSourceDataMapper;
import com.gsafety.dawn.community.manage.service.datamappers.EpidemicPersonMapper;
import com.gsafety.dawn.community.manage.service.entity.DSourceDataEntity;
import com.gsafety.dawn.community.manage.service.entity.EpidemicPersonEntity;
import com.gsafety.dawn.community.manage.service.repository.DSourceDataRepository;
import com.gsafety.dawn.community.manage.service.repository.EpidemicPersonRepository;
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

    @Override
    public EpidemicPersonModel addOneEpidemicPerson(EpidemicPersonModel epidemicPersonModel) {
        epidemicPersonModel.setMultiTenancy("123456");
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
                epidemicPersonModel.getMultiTenancy(), epidemicPersonModel.getExpendProperty(),epidemicPersonModel.getMobileNumber())) {
            result = epidemicPersonMapper.entityToModel(epidemicPersonRepository.getOne(id));
        }

        return result;
    }

    @Override
    public List<DiagnosisCountModel> DiagnosisCount() {
        List<DiagnosisCountModel> result = new ArrayList<>();
        List<DSourceDataEntity> dSourceDataEntities = dSourceDataRepository.queryByDataSourceIdOrderBySortAsc(diagnosisId);
        dSourceDataEntities.forEach(dSourceDataEntity -> {
            DiagnosisCountModel diagnosisCountModel=new DiagnosisCountModel();
            diagnosisCountModel.setdSourceDataModel(dSourceDataMapper.entityToModel(dSourceDataEntity));
            diagnosisCountModel.setCount(epidemicPersonRepository.queryCountByDiagnosisSituation(dSourceDataEntity.getId()));
            result.add(diagnosisCountModel);
        });
        return result;
    }
}

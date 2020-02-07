package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.manage.contract.model.EpidemicPersonModel;
import com.gsafety.dawn.community.manage.contract.service.EpidemicPersonService;
import com.gsafety.dawn.community.manage.service.datamappers.EpidemicPersonMapper;
import com.gsafety.dawn.community.manage.service.entity.EpidemicPersonEntity;
import com.gsafety.dawn.community.manage.service.repository.EpidemicPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EpidemicPersonServiceImpl implements EpidemicPersonService {
    @Autowired
    private EpidemicPersonMapper epidemicPersonMapper;
    @Autowired
    private EpidemicPersonRepository epidemicPersonRepository;

    @Override
    public EpidemicPersonModel addOneEpidemicPerson(EpidemicPersonModel epidemicPersonModel) {
        EpidemicPersonEntity epidemicPersonEntity = epidemicPersonMapper.modelToEntity(epidemicPersonModel);
        return epidemicPersonMapper.entityToModel(epidemicPersonRepository.save(epidemicPersonEntity));
    }

    @Override
    public EpidemicPersonModel modifyOneEpidemicPerson(String id, EpidemicPersonModel epidemicPersonModel) {
        EpidemicPersonModel result = new EpidemicPersonModel();
        if (id == null || !id.equals(epidemicPersonModel.getId())) {
            return null;
        }
        if (epidemicPersonRepository.existsById(id)) {
            if (0 < epidemicPersonRepository.updateEpidemicPerson(epidemicPersonModel.getId(), epidemicPersonModel.getName(),
                    epidemicPersonModel.getGender(), epidemicPersonModel.getAddress(), epidemicPersonModel.getDistrict(),
                    epidemicPersonModel.getMedicalCondition(), epidemicPersonModel.getSpecialSituation(),
                    epidemicPersonModel.getSubmitTime(), epidemicPersonModel.getNote(), epidemicPersonModel.getDiseaseTime(),
                    epidemicPersonModel.getMultiTenancy(), epidemicPersonModel.getExpendProperty())) {
                    result=epidemicPersonMapper.entityToModel(epidemicPersonRepository.getOne(id));
            }
        }
        return result;
    }
}

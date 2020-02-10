package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.manage.contract.model.BasicInformationModel;
import com.gsafety.dawn.community.manage.contract.service.BasicInformationService;
import com.gsafety.dawn.community.manage.service.datamappers.BasciInformationMapper;
import com.gsafety.dawn.community.manage.service.entity.BasicInformationEntity;
import com.gsafety.dawn.community.manage.service.repository.BasicInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * description:
 *
 * @outhor liujian
 * @create 2020-02-09 16:15
 */
@Service
@Transactional
public class BasicInformationServiceImpl implements BasicInformationService {

    @Autowired
    BasciInformationMapper basciInformationMapper;

    @Autowired
    BasicInformationRepository basicInformationRepository;

    @Override
    public void addPerson(BasicInformationModel basicInformationModel) {
        List<BasicInformationEntity> allByNameAndPhone = basicInformationRepository.findAllByNameAndPhone(basicInformationModel.getName(), basicInformationModel.getPhone());
        if(allByNameAndPhone.isEmpty()){
           basicInformationModel.setId(UUID.randomUUID().toString());
           basicInformationModel.setCode(UUID.randomUUID().toString());
           basicInformationRepository.saveAndFlush(basciInformationMapper.modelToEntity(basicInformationModel));
        }
    }

    @Override
    public void updatePerson(BasicInformationModel basicInformationModel) {
        List<BasicInformationEntity> allByNameAndPhone = basicInformationRepository.findAllByNameAndPhone(basicInformationModel.getName(), basicInformationModel.getPhone());
        if(!allByNameAndPhone.isEmpty()){
            basicInformationRepository.saveAndFlush(basciInformationMapper.modelToEntity(basicInformationModel));
        }
    }
}

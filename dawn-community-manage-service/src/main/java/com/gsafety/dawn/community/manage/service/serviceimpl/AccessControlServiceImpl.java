package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.manage.contract.model.AccessControlModel;
import com.gsafety.dawn.community.manage.contract.service.AccessControlService;
import com.gsafety.dawn.community.manage.service.datamappers.AccessControlMapper;
import com.gsafety.dawn.community.manage.service.entity.AccessControlEntity;
import com.gsafety.dawn.community.manage.service.repository.AccessControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class AccessControlServiceImpl implements AccessControlService {
    @Autowired
    private AccessControlMapper accessControlMapper;
    @Autowired
    private AccessControlRepository accessControlRepository;


    @Override
    public AccessControlModel addOneAccessControl(AccessControlModel accessControlModel) {
        AccessControlEntity accessControlEntity = accessControlMapper.modelToEntity(accessControlModel);
        return accessControlMapper.entityToModel(accessControlRepository.saveAndFlush(accessControlEntity));
    }

    @Override
    public AccessControlModel modifyOneAccessControl(String id, AccessControlModel accessControlModel) {
        AccessControlModel result = new AccessControlModel();
        if (id == null || !id.equals(accessControlModel.getId()) || !accessControlRepository.existsById(id)) {
            return result;
        }
        AccessControlEntity accessControlEntity=accessControlMapper.modelToEntity(accessControlModel);
        return accessControlMapper.entityToModel(accessControlRepository.saveAndFlush(accessControlEntity));
    }

    @Override
    public Boolean deleteOneAccessControl(String id) {
        Boolean result = false;
        if (accessControlRepository.existsById(id)) {
            accessControlRepository.deleteById(id);
            result = true;
        }
        return result;
    }
}

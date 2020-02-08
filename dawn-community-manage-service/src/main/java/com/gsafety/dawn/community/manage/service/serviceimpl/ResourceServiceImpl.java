package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.common.util.DateUtil;
import com.gsafety.dawn.community.manage.contract.model.ResourceInOutRecordModel;
import com.gsafety.dawn.community.manage.contract.model.ResourceModel;
import com.gsafety.dawn.community.manage.contract.service.ResourceInOutRecordService;
import com.gsafety.dawn.community.manage.contract.service.ResourceService;
import com.gsafety.dawn.community.manage.service.datamappers.ResourceMapper;
import com.gsafety.dawn.community.manage.service.entity.ResourceEntity;
import com.gsafety.dawn.community.manage.service.entity.ResourceInOutRecordEntity;
import com.gsafety.dawn.community.manage.service.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * @create 2020-02-07 23:02
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    /**
     * The Resource repository.
     */
    @Autowired
    ResourceRepository resourceRepository;
    /**
     * The Resource mapper.
     */
    @Autowired
    ResourceMapper resourceMapper;

    @Autowired
    ResourceInOutRecordService recordService;

    @Override
    public ResourceModel addResource(ResourceModel resourceModel) {
        resourceModel.setId(UUID.randomUUID().toString());
        resourceModel.setCode(UUID.randomUUID().toString());
        ResourceEntity resourceEntity = resourceMapper.modelToEntity(resourceModel);
        Timestamp ts = DateUtil.convertNowDate();
        resourceEntity.setCreateTime(ts);
        resourceEntity.setUpdateTime(ts);
        resourceEntity.setDelete(false);
        ResourceEntity result = resourceRepository.save(resourceEntity);

        ResourceInOutRecordModel resourceInOutRecordModel = new ResourceInOutRecordModel();
        resourceInOutRecordModel.setCount(resourceModel.getCount());
        resourceInOutRecordModel.setInOutStatus(true);
        resourceInOutRecordModel.setResourceId(result.getId());
        recordService.addResource(resourceInOutRecordModel);

        return resourceMapper.entityToModel(result);
    }

    @Override
    public ResourceModel updateResource(ResourceModel resourceModel) {
        boolean exists = resourceRepository.existsById(resourceModel.getId());
        if(exists){
            ResourceEntity resourceEntity = resourceMapper.modelToEntity(resourceModel);
            Timestamp ts = DateUtil.convertNowDate();
            resourceEntity.setUpdateTime(ts);
            return resourceMapper.entityToModel(resourceRepository.save(resourceEntity));
        }
        return null;
    }

    @Override
    public Boolean deleteResource(String resId) {
        boolean exists = resourceRepository.existsById(resId);
        boolean flag = false;
        if(exists){
            resourceRepository.updateOneDSourceData(resId, true);
            flag = true;
        }
        return flag;
    }

}

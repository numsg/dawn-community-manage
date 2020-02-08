package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.common.util.DateUtil;
import com.gsafety.dawn.community.manage.contract.model.ResourceInOutRecordModel;
import com.gsafety.dawn.community.manage.contract.service.ResourceInOutRecordService;
import com.gsafety.dawn.community.manage.service.datamappers.ResourceInOutRecordMapper;
import com.gsafety.dawn.community.manage.service.entity.ResourceEntity;
import com.gsafety.dawn.community.manage.service.entity.ResourceInOutRecordEntity;
import com.gsafety.dawn.community.manage.service.repository.ResourceInOutRecordRepository;
import com.gsafety.dawn.community.manage.service.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * description:
 *
 * @outhor liujian
 * @create 2020-02-07 23:01
 */
@Service
@Transactional
public class ResourceInoutRecordServiceImpl implements ResourceInOutRecordService {

    @Autowired
    ResourceInOutRecordRepository resourceInOutRecordRepository;

    @Autowired
    ResourceInOutRecordMapper resourceInOutRecordMapper;

    @Autowired
    ResourceRepository resourceRepository;

    @Override
    public ResourceInOutRecordModel addResource(ResourceInOutRecordModel resourceInOutRecordModel) {
        boolean exists = resourceRepository.existsById(resourceInOutRecordModel.getResourceId());
        if (exists) {
            ResourceInOutRecordEntity resourceInOutRecordEntity = resourceInOutRecordMapper.modelToEntity(resourceInOutRecordModel);
            ResourceEntity resourceEntity = resourceRepository.getOne(resourceInOutRecordModel.getResourceId());
            Timestamp ts = DateUtil.convertNowDate();
            resourceInOutRecordEntity.setCreateTime(ts);
            resourceEntity.setId(resourceInOutRecordModel.getResourceId());
            resourceInOutRecordEntity.setId(UUID.randomUUID().toString());
            resourceInOutRecordEntity.setResourceEntity(resourceEntity);
            return resourceInOutRecordMapper.entityToModel(resourceInOutRecordRepository.save(resourceInOutRecordEntity));
        }
        return null;
    }

}

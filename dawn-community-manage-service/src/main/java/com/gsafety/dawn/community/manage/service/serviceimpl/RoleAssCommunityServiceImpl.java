package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.common.util.DateUtil;
import com.gsafety.dawn.community.common.util.StringUtil;
import com.gsafety.dawn.community.manage.contract.model.RoleAssCommunityModel;
import com.gsafety.dawn.community.manage.contract.service.RoleAssCommunityService;
import com.gsafety.dawn.community.manage.service.datamappers.RoleAssCommunityMapper;
import com.gsafety.dawn.community.manage.service.entity.RoleAssCommunityEntity;
import com.gsafety.dawn.community.manage.service.repository.RoleAssCommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * @create 2020-02-12 13:12
 */

@Service
@Transactional
public class RoleAssCommunityServiceImpl implements RoleAssCommunityService {

    @Autowired
    RoleAssCommunityMapper roleAssCommunityMapper;

    @Autowired
    RoleAssCommunityRepository roleAssCommunityRepository;

    private final static Timestamp TS = DateUtil.convertNowDate();

    @Override
    public List<RoleAssCommunityModel> addRoleAssCom(List<RoleAssCommunityModel> roleAssCommunityModels) {
        if (CollectionUtils.isEmpty(roleAssCommunityModels))
            return null;
        roleAssCommunityModels.forEach(a -> {
            a.setCreateTime(TS);
            a.setUpdateTime(TS);
            a.setId(UUID.randomUUID().toString());
        });
        List<RoleAssCommunityEntity> roleAssCommunityEntitys = roleAssCommunityMapper.modelsToEntities(roleAssCommunityModels);
        List<RoleAssCommunityEntity> result = roleAssCommunityRepository.saveAll(roleAssCommunityEntitys);
        return roleAssCommunityMapper.entitiesToModels(result);
    }

    @Override
    public List<RoleAssCommunityModel> updateRoleAssCom(List<RoleAssCommunityModel> roleAssCommunityModels) {
        if (CollectionUtils.isEmpty(roleAssCommunityModels))
            return null;
        List<RoleAssCommunityEntity> entities = roleAssCommunityMapper.modelsToEntities(roleAssCommunityModels);
        List<RoleAssCommunityEntity> result = new ArrayList<>();
        for(int i = 0 ; i < entities.size() ; i++){
            RoleAssCommunityEntity roleAssCommunityEntity = entities.get(i);
            if(roleAssCommunityEntity.getId() == null || roleAssCommunityRepository.findById(roleAssCommunityEntity.getId()) == null)
                continue;
            roleAssCommunityEntity.setUpdateTime(TS);
            result.add(roleAssCommunityRepository.save(roleAssCommunityEntity));
        }
        return roleAssCommunityMapper.entitiesToModels(result);
    }

    @Override
    public boolean deleteRoleAssCom(List<String> roleAssCommunityIds) {
        if(CollectionUtils.isEmpty(roleAssCommunityIds))
            return false;
        roleAssCommunityIds.forEach(a -> {
            roleAssCommunityRepository.deleteById(a);
        });
        return true;
    }


    @Override
    public List<RoleAssCommunityModel> queryByroleIds(List<String> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return Collections.emptyList();
        }
        List<RoleAssCommunityEntity> entities = roleAssCommunityRepository.findByRolesInformationIn(roleIds);
        return roleAssCommunityMapper.entitiesToModels(entities);
    }

    @Override
    public List<RoleAssCommunityModel> queryByRoleCodes(List<String> codes) {
        if (CollectionUtils.isEmpty(codes)) {
            return Collections.emptyList();
        }
        List<RoleAssCommunityEntity> entities = roleAssCommunityRepository.findByRolesInformationIn(codes);
        return roleAssCommunityMapper.entitiesToModels(entities);
    }
}

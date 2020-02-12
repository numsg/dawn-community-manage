package com.gsafety.dawn.community.manage.service.datamappers;


import com.gsafety.dawn.community.manage.contract.model.RoleAssCommunityModel;
import com.gsafety.dawn.community.manage.service.entity.RoleAssCommunityEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleAssCommunityMapper {

    RoleAssCommunityModel entityToModel(RoleAssCommunityEntity communityEntity);

    RoleAssCommunityEntity modelToEntity(RoleAssCommunityModel communityModel);

    List<RoleAssCommunityModel> entitiesToModels(List<RoleAssCommunityEntity> communityEntities);

    List<RoleAssCommunityEntity> modelsToEntities(List<RoleAssCommunityModel> resourceModels);

}

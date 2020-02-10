package com.gsafety.dawn.community.manage.service.datamappers;

import com.gsafety.dawn.community.manage.contract.model.AccessControlModel;
import com.gsafety.dawn.community.manage.service.entity.AccessControlEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccessControlMapper {
    AccessControlEntity modelToEntity(AccessControlModel accessControlModel);

    AccessControlModel entityToModel(AccessControlEntity accessControlEntity);

    List<AccessControlEntity> modelsToEntities(List<AccessControlModel> accessControlModels);

    List<AccessControlModel> entitiesToModels(List<AccessControlEntity> accessControlEntities);
}

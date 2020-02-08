package com.gsafety.dawn.community.manage.service.datamappers;


import com.gsafety.dawn.community.manage.contract.model.ResourceModel;
import com.gsafety.dawn.community.manage.service.entity.ResourceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResourceMapper {

    /**
     * Entity to model resource model.
     *
     * @param resourceEntity the resource entity
     * @return the resource model
     */
    ResourceModel entityToModel(ResourceEntity resourceEntity);

    /**
     * Model to entity resource entity.
     *
     * @param resourceModel the resource model
     * @return the resource entity
     */
    ResourceEntity modelToEntity(ResourceModel resourceModel);

    /**
     * Entities to models list.
     *
     * @param resourceEntities the resource entities
     * @return the list
     */
    List<ResourceModel> entitiesToModels(List<ResourceEntity> resourceEntities);

    /**
     * Models to entities list.
     *
     * @param resourceModels the resource models
     * @return the list
     */
    List<ResourceEntity> modelsToEntities(List<ResourceModel> resourceModels);

}

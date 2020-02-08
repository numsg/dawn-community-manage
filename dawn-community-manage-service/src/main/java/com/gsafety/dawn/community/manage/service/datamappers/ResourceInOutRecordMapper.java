package com.gsafety.dawn.community.manage.service.datamappers;

import com.gsafety.dawn.community.manage.contract.model.ResourceInOutRecordModel;
import com.gsafety.dawn.community.manage.service.entity.ResourceInOutRecordEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResourceInOutRecordMapper {

    /**
     * Entity to model resource in out record model.
     *
     * @param resourceInOutRecordEntity the resource in out record entity
     * @return the resource in out record model
     */
    ResourceInOutRecordModel entityToModel(ResourceInOutRecordEntity resourceInOutRecordEntity);


    /**
     * Model to entity resource in out record entity.
     *
     * @param resourceInOutRecordModel the resource in out record model
     * @return the resource in out record entity
     */
    ResourceInOutRecordEntity modelToEntity(ResourceInOutRecordModel resourceInOutRecordModel);


    /**
     * Entities to models list.
     *
     * @param resourceInOutRecordEntities the resource in out record entities
     * @return the list
     */
    List<ResourceInOutRecordModel> entitiesToModels(List<ResourceInOutRecordEntity> resourceInOutRecordEntities);


    /**
     * Models to entities list.
     *
     * @param resourceInOutRecordModels the resource in out record models
     * @return the list
     */
    List<ResourceInOutRecordEntity> modelsToEntities(List<ResourceInOutRecordModel> resourceInOutRecordModels);


}

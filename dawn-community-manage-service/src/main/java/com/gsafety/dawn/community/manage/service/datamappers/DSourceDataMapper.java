package com.gsafety.dawn.community.manage.service.datamappers;


import com.gsafety.dawn.community.manage.contract.model.DSourceDataModel;
import com.gsafety.dawn.community.manage.service.entity.DSourceDataEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * The interface DataSourceData mapper.
 */
@Mapper(componentModel = "spring")
public interface DSourceDataMapper {
    /**
     * The data-source data entity to data-source data model.
     *
     * @param dSourceDataEntity the data-source data entity
     * @return the data-source data model
     */
    @Mapping(source = "dataSourceEntity.id",target = "dataSourceId")
    DSourceDataModel entityToModel(DSourceDataEntity dSourceDataEntity);

    /**
     * The data-source data model to data-source data entity.
     *
     * @param dSourceDataModel the data-source data model
     * @return the data-source data entity
     */
    DSourceDataEntity modelToEntity(DSourceDataModel dSourceDataModel);

    /**
     * Entities to models list.
     *
     * @param dSourceDataEntities the d source data entities
     * @return the list
     */
    List<DSourceDataModel> entitiesToModels(List<DSourceDataEntity> dSourceDataEntities);

    /**
     * Models to entities list.
     *
     * @param dSourceDataModels the d source data models
     * @return the list
     */
    List<DSourceDataEntity> modelsToEntities(List<DSourceDataModel> dSourceDataModels);
}

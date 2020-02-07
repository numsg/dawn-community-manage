package com.gsafety.dawn.community.manage.service.datamappers;

import com.gsafety.dawn.community.manage.contract.model.DataSourceModel;
import com.gsafety.dawn.community.manage.service.entity.DataSourceEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * The interface DataSource mapper.
 */
@Mapper(componentModel = "spring")
public interface DataSourceMapper {
    /**
     * The data source entity to data source model.
     *
     * @param dataSourceEntity the data source entity
     * @return the data source model
     */
    DataSourceModel entityToModel(DataSourceEntity dataSourceEntity);

    /**
     * The data source model to data source entity.
     *
     * @param dataSourceModel the data source model
     * @return the data source entity
     */
    DataSourceEntity modelToEntity(DataSourceModel dataSourceModel);

    /**
     * Entities to models list.
     *
     * @param dataSourceEntities the data source entities
     * @return the list
     */
    List<DataSourceModel> entitiesToModels(List<DataSourceEntity> dataSourceEntities);
}

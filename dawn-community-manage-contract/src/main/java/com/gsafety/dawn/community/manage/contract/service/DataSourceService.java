package com.gsafety.dawn.community.manage.contract.service;


import com.gsafety.dawn.community.manage.contract.model.DataSourceModel;

import java.util.List;

/**
 * The interface data source service.
 */
public interface DataSourceService {
    /**
     * Add one data source entity.
     *
     * @param dataSourceModel the data source model.
     * @return the data source model.
     */
    DataSourceModel addOneDataSource(DataSourceModel dataSourceModel);

    /**
     * Add batch data source list.
     *
     * @param dataSourceModels the data source models
     * @param isResource       the is resource
     * @return the list
     */
    List<DataSourceModel> addBatchDataSource(List<DataSourceModel> dataSourceModels, boolean isResource);

    /**
     * Delete one data source entity
     *
     * @param dataSourceId the data source id.
     * @return boolean boolean
     */
    Boolean deleteOneDataSource(String dataSourceId);

    /**
     * Update one data source entity.
     *
     * @param dataSourceModel the data source model.
     * @return the data source model.
     */
    DataSourceModel updateOneDataSource(DataSourceModel dataSourceModel);


    /**
     * Gets all data sources.
     *
     * @param isUseRMS the is use rms
     * @return the all data sources
     */
    List<DataSourceModel> getAllDataSources(Boolean isUseRMS);
}

package com.gsafety.dawn.community.manage.contract.service;

import com.gsafety.dawn.community.manage.contract.model.DSourceDataModel;

import java.util.List;

/**
 * The interface data source service.
 */
public interface DSourceDataService {
    /**
     * Add one data-source data entity.
     *
     * @param dSourceDataModel the data-source data model.
     * @return the data-source data model.
     */
    DSourceDataModel addOneDSourceData(DSourceDataModel dSourceDataModel);

    /**
     * Add batch data-source data entity.
     *
     * @param dSourceDataModels the data-source data model.
     * @return the data-source data model.
     */
    List<DSourceDataModel> addBatchDSourceData(List<DSourceDataModel> dSourceDataModels, boolean isResource);

    /**
     * Delete  data-source data entity.
     *
     * @param dSourceDataId the data-source data id
     * @return boolean boolean
     */
    Boolean deleteDSourceData(String dSourceDataId);

    /**
     * update  data-source data entity.
     *
     * @param dSourceDataModel the data-source data model.
     * @return the data-source data model.
     */
    DSourceDataModel updateOneDSourceData(DSourceDataModel dSourceDataModel);

    /**
     * Gets one d source data by id.
     *
     * @param dSourceDataIds the d source data ids
     * @return the one d source data by id
     */
    List<DSourceDataModel> getSomeDSourceDataByIds(List<String> dSourceDataIds);


    /**
     * Gets one d source data by id.
     *
     * @param id the id
     * @return the one d source data by id
     */
    DSourceDataModel getOneDSourceDataById(String id);

    /**
     * Gets d source data by data source id.
     *
     * @param dataSourceId the data source id
     * @return the d source data by data source id
     */
    List<DSourceDataModel> getDSourceDataByDataSourceId(String dataSourceId);

    /**
     * Gets all d source data by data source id.
     *
     * @param originalId the origin id
     * @param isUseRMS   the is use rms
     * @return the all d source data by data source id
     */
    List<DSourceDataModel> getAllDSourceDataByDataSourceId(String originalId, Boolean isUseRMS);

    /**
     * Update d source data sort list.
     *
     * @param dSourceDataModels the d source data models
     * @return the list
     */
    List<DSourceDataModel> updateDSourceDataSort(List<DSourceDataModel> dSourceDataModels);
}

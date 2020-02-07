package com.gsafety.dawn.community.manage.contract.service;



import com.gsafety.dawn.community.manage.contract.model.CellTypeModel;

import java.util.List;

/**
 * The interface Cell type service.
 */
public interface CellTypeService {

    /**
     * Add cell type cell type model.
     *
     * @param cellTypeModel the cell type model
     * @return the cell type model
     */
    CellTypeModel addCellType(CellTypeModel cellTypeModel);

    /**
     * Delete cell type boolean.
     *
     * @param cellTypeId the cell type id
     * @return the boolean
     */
    Boolean deleteCellType(String cellTypeId);

    /**
     * Update cell type cell type model.
     *
     * @param cellTypeModel the cell type model
     * @return the cell type model
     */
    CellTypeModel updateCellType(CellTypeModel cellTypeModel);


    /**
     * Update cell type sort list.
     *
     * @param cellTypeModels the cell type models
     * @return the list
     */
    List<CellTypeModel> updateCellTypeSort(List<CellTypeModel> cellTypeModels);
}

package com.gsafety.dawn.community.manage.contract.service;

import com.gsafety.dawn.community.manage.contract.model.CellModel;

/**
 * The interface Cell service.
 */
public interface CellService {
    /**
     * Add one cell cell model.
     *
     * @param cellModel the cell model
     * @return the cell model
     */
    CellModel addOneCell(CellModel cellModel);

    /**
     * Update one cell cell model.
     *
     * @param cellModel the cell model
     * @return the cell model
     */
    CellModel updateOneCell(CellModel cellModel);

    /**
     * Delete one cell boolean.
     *
     * @param cellId the cell id
     * @return the boolean
     */
    Boolean deleteOneCell(String cellId);

    /**
     * Judge name is repeat boolean.
     *
     * @param name the name
     * @return the boolean
     */
    Boolean judgeNameIsRepeat(String name);

  //  Boolean addManyCells(Integer count,String cellId);
}

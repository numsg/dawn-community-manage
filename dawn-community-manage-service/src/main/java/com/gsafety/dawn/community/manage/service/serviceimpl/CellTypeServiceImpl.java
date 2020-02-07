package com.gsafety.dawn.community.manage.service.serviceimpl;


import com.gsafety.dawn.community.manage.contract.model.CellTypeModel;
import com.gsafety.dawn.community.manage.contract.service.CellTypeService;
import com.gsafety.dawn.community.manage.service.datamappers.CellTypeMapper;
import com.gsafety.dawn.community.manage.service.entity.CellEntity;
import com.gsafety.dawn.community.manage.service.entity.CellTypeEntity;
import com.gsafety.dawn.community.manage.service.repository.CellRepository;
import com.gsafety.dawn.community.manage.service.repository.CellTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Cell type service.
 */
@Service
public class CellTypeServiceImpl implements CellTypeService {
    @Autowired
    private CellTypeRepository cellTypeRepository;
    @Autowired
    private CellTypeMapper cellTypeMapper;
    @Autowired
    private CellRepository cellRepository;

    /**
     * Add cell type cell type model.
     *
     * @param cellTypeModel the cell type model
     * @return the cell type model
     */
    @Override
    public CellTypeModel addCellType(CellTypeModel cellTypeModel) {
        CellTypeEntity cellTypeEntity = cellTypeMapper.cellTypeModelToEntity(cellTypeModel);
        cellTypeEntity = cellTypeRepository.saveAndFlush(cellTypeEntity);
        return cellTypeMapper.cellTypeEntityToModel(cellTypeEntity);
    }

    /**
     * Delete cell type boolean.
     *
     * @param cellTypeId the cell type id
     * @return the boolean
     */
    @Override
    public Boolean deleteCellType(String cellTypeId) {
        Boolean result = false;
        List<CellEntity> cellEntities =cellRepository.vagueQueryByCellTypeId(cellTypeId);
        List<CellTypeEntity> cellTypeEntities=cellTypeRepository.findByPid(cellTypeId);
       if (cellEntities.isEmpty() && cellTypeRepository.exists(cellTypeId) && cellTypeEntities.isEmpty()) {
            cellTypeRepository.delete(cellTypeId);
            result = true;
       }
        return result;
    }

    /**
     * Update cell type cell type model.
     *
     * @param cellTypeModel the cell type model
     * @return the cell type model
     */
    @Override
    public CellTypeModel updateCellType(CellTypeModel cellTypeModel) {
        CellTypeModel result = null;
        if (0 < cellTypeRepository.updateCellType(cellTypeModel.getId(), cellTypeModel.getName(), cellTypeModel.getDescription(), cellTypeModel.getPid(),cellTypeModel.getImage(),cellTypeModel.getImgColor())) {
            result = cellTypeMapper.cellTypeEntityToModel(cellTypeRepository.getOne(cellTypeModel.getId()));
        }
        return result;
    }

    /**
     * Update cell type sort list.
     *
     * @param cellTypeModels the cell type models
     * @return the list
     */
    @Override
    public List<CellTypeModel> updateCellTypeSort(List<CellTypeModel> cellTypeModels) {
        List<CellTypeModel> result= new ArrayList<>();
        for (CellTypeModel cellTypeModel : cellTypeModels) {
            if (cellTypeRepository.updateCellTypeSort(cellTypeModel.getId(),cellTypeModel.getSort(),cellTypeModel.getPid())>0){
                result.add(cellTypeMapper.cellTypeEntityToModel(cellTypeRepository.getOne(cellTypeModel.getId())));
            }else{
                // 修改失败
                result = new ArrayList<>();
                break;
            }
        }
        return result;
    }
}

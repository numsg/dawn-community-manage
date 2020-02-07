package com.gsafety.dawn.community.manage.service.datamappers;



import com.gsafety.dawn.community.manage.contract.model.CellTypeModel;
import com.gsafety.dawn.community.manage.service.entity.CellTypeEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * The interface Cell type mapper.
 */
@Mapper(componentModel = "spring")
public interface CellTypeMapper {
    /**
     * Cell type entity to model cell type model.
     *
     * @param cellTypeEntity the cell type entity
     * @return the cell type model
     */
    CellTypeModel cellTypeEntityToModel(CellTypeEntity cellTypeEntity);

    /**
     * Cell type model to entity cell type entity.
     *
     * @param cellTypeModel the cell type model
     * @return the cell type entity
     */
    CellTypeEntity cellTypeModelToEntity(CellTypeModel cellTypeModel);

    /**
     * Cell type entities to models list.
     *
     * @param cellTypeEntities the cell type entities
     * @return the list
     */
    List<CellTypeModel> cellTypeEntitiesToModels(List<CellTypeEntity> cellTypeEntities);

    /**
     * Cell type models to entities list.
     *
     * @param cellTypeModels the cell type models
     * @return the list
     */
    List<CellTypeEntity> cellTypeModelsToEntities(List<CellTypeModel> cellTypeModels);
}

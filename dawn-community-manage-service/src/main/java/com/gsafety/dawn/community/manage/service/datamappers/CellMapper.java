package com.gsafety.dawn.community.manage.service.datamappers;


import com.gsafety.dawn.community.manage.contract.model.CellModel;
import com.gsafety.dawn.community.manage.service.entity.CellEntity;
import org.mapstruct.Mapper;

/**
 * The interface Cell mapper.
 */
@Mapper(componentModel = "spring")
public interface CellMapper {

    /**
     * Cell entity to model cell model.
     *
     * @param cellEntity the cell entity
     * @return the cell model
     */
    CellModel cellEntityToModel(CellEntity cellEntity);

    /**
     * Cell model to entity cell entity.
     *
     * @param cellModel the cell model
     * @return the cell entity
     */
    // @Mapping(source = "cellTypeId",target = "cellTypeEntity.id")
    // @Mapping(source = "eventTypeId",target = "eventTypeEntity.id")
    CellEntity cellModelToEntity(CellModel cellModel);

}

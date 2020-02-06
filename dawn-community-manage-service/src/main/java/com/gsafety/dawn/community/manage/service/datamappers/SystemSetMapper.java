package com.gsafety.dawn.community.manage.service.datamappers;

import com.gsafety.dawn.community.manage.contract.model.SystemSetModel;
import com.gsafety.dawn.community.manage.service.entity.SystemSetEntity;
import org.mapstruct.Mapper;

/**
 * The interface System set mapper.
 */
@Mapper(componentModel = "spring")
public interface SystemSetMapper {

    /**
     * Entity to model system set model.
     *
     * @param systemSetEntity the system set entity
     * @return the system set model
     */
    SystemSetModel entityToModel(SystemSetEntity systemSetEntity);

    /**
     * Model to entity system set entity.
     *
     * @param systemSetModel the system set model
     * @return the system set entity
     */
    SystemSetEntity modelToEntity(SystemSetModel systemSetModel);
}

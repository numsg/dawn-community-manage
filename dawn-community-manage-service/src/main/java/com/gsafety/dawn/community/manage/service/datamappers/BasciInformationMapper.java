package com.gsafety.dawn.community.manage.service.datamappers;


import com.gsafety.dawn.community.manage.contract.model.BasicInformationModel;
import com.gsafety.dawn.community.manage.service.entity.BasicInformationEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BasciInformationMapper {

    BasicInformationModel entityToModel(BasicInformationEntity entity);

    BasicInformationEntity modelToEntity(BasicInformationModel model);

    List<BasicInformationModel> entitiesToModels(List<BasicInformationEntity> entities);

    List<BasicInformationEntity> modelsToEntities(List<BasicInformationModel> models);

}

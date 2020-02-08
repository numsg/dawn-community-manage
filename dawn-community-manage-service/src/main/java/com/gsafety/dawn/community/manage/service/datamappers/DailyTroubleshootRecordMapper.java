package com.gsafety.dawn.community.manage.service.datamappers;


import com.gsafety.dawn.community.manage.contract.model.DailyTroubleshootRecordModel;
import com.gsafety.dawn.community.manage.service.entity.DailyTroubleshootRecordEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DailyTroubleshootRecordMapper {

    DailyTroubleshootRecordModel entityToModel(DailyTroubleshootRecordEntity dailyTroubleshootRecordEntity);

    DailyTroubleshootRecordEntity modelToEntity(DailyTroubleshootRecordModel dailyTroubleshootRecordModel);

    List<DailyTroubleshootRecordModel> entitiesToModels(List<DailyTroubleshootRecordEntity> dailyTroubleshootRecordEntities);

    List<DailyTroubleshootRecordEntity> modelsToEntities(List<DailyTroubleshootRecordModel> models);

}

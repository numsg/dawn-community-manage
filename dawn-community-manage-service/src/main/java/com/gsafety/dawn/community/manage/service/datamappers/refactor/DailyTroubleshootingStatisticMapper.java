package com.gsafety.dawn.community.manage.service.datamappers.refactor;


import com.gsafety.dawn.community.manage.contract.model.refactor.DailyTroubleshootingStatisticModel;
import com.gsafety.dawn.community.manage.service.entity.refactor.DailyTroubleshootingStatisticEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DailyTroubleshootingStatisticMapper {


    DailyTroubleshootingStatisticModel entityToModel(DailyTroubleshootingStatisticEntity statisticEntity);


    List<DailyTroubleshootingStatisticModel> entitiesToModels(List<DailyTroubleshootingStatisticEntity> statisticEntities);
}

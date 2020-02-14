package com.gsafety.dawn.community.manage.service.datamappers.refactor;

import com.gsafety.dawn.community.manage.contract.model.refactor.TroubleshootRecord;
import com.gsafety.dawn.community.manage.service.entity.refactor.TroubleshootRecordEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PersonBaseMapper.class})
public interface TroubleshootRecordMapper {

    TroubleshootRecordEntity modelToEntity(TroubleshootRecord model);

    TroubleshootRecord entityToModel(TroubleshootRecordEntity model);

}

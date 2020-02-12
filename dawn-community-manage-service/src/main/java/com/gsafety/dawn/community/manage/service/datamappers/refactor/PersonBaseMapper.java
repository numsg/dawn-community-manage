package com.gsafety.dawn.community.manage.service.datamappers.refactor;

import com.gsafety.dawn.community.manage.contract.model.refactor.PersonBase;
import com.gsafety.dawn.community.manage.service.entity.refactor.PersonBaseEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",uses = {TroubleshootRecordMapper.class})
public interface PersonBaseMapper {

    PersonBaseEntity modelToEntity(PersonBase model);

}

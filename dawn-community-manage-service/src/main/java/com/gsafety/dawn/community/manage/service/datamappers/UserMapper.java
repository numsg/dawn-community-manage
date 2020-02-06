package com.gsafety.dawn.community.manage.service.datamappers;


import com.gsafety.dawn.community.manage.contract.model.AppUserInfo;
import com.gsafety.dawn.community.manage.service.entity.AppUserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Created by numsg on 2017/3/8.
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "id", target = "userId")
    AppUserInfo entityToModel(AppUserEntity appUserEntity);

    @Mapping(ignore = true, target = "id")
    AppUserEntity modelToEntity(AppUserInfo appUserInfo);

    List<AppUserInfo> entitiestoModels(List<AppUserEntity> appUserEntity);

    List<AppUserEntity> modelstoEntities(List<AppUserInfo> appUserEntity);
}

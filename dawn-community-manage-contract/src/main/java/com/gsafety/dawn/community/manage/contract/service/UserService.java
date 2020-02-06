package com.gsafety.dawn.community.manage.contract.service;


import com.gsafety.dawn.community.manage.contract.model.AppUserInfo;

import java.util.List;

/**
 * Created by numsg on 2017/3/3.
 */
public interface UserService {

    AppUserInfo save(AppUserInfo appUserEntity);
    List<AppUserInfo> findAll();
    boolean testTrans();

}

package com.gsafety.dawn.community.manage.contract.service;

import com.gsafety.dawn.community.manage.contract.model.BasicInformationModel;

public interface BasicInformationService {

    // 新增
    void addPerson(BasicInformationModel basicInformationModel);

    // 修改
    void updatePerson(BasicInformationModel basicInformationModel);

}

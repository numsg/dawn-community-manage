package com.gsafety.dawn.community.manage.contract.service;

import com.gsafety.dawn.community.manage.contract.model.EpidemicPersonModel;

public interface EpidemicPersonService {
    // 添加
   EpidemicPersonModel addOneEpidemicPerson(EpidemicPersonModel epidemicPersonModel);
    // 修改
    EpidemicPersonModel modifyOneEpidemicPerson(String id,EpidemicPersonModel epidemicPersonModel);
}

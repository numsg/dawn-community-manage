package com.gsafety.dawn.community.manage.contract.service;

import com.gsafety.dawn.community.manage.contract.model.DiagnosisCountModel;
import com.gsafety.dawn.community.manage.contract.model.EpidemicPersonModel;

import java.util.List;

public interface EpidemicPersonService {
    // 添加
   EpidemicPersonModel addOneEpidemicPerson(EpidemicPersonModel epidemicPersonModel);
    // 修改
    EpidemicPersonModel modifyOneEpidemicPerson(String id,EpidemicPersonModel epidemicPersonModel);

    // 统计
    List<DiagnosisCountModel> DiagnosisCount();
}

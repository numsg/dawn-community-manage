package com.gsafety.dawn.community.manage.contract.service;

import com.gsafety.dawn.community.manage.contract.model.total.DiagnosisCountModel;
import com.gsafety.dawn.community.manage.contract.model.EpidemicPersonModel;
import com.gsafety.dawn.community.manage.contract.model.total.SpecialCountModel;

import java.util.List;

public interface EpidemicPersonService {
    // 添加
    EpidemicPersonModel addOneEpidemicPerson(EpidemicPersonModel epidemicPersonModel);

    // 修改
    EpidemicPersonModel modifyOneEpidemicPerson(String id, EpidemicPersonModel epidemicPersonModel);

    // 删除
    Boolean deleteOneEpidemicPerson(String id);

    // 统计
    List<DiagnosisCountModel> diagnosisCount(String districtCode);

    List<SpecialCountModel> diagnosisCountWithConfirmedAndSuspected(String communityId);

    List<SpecialCountModel> diagnosisCountWithHealthAndDeath(String communityId);
}

package com.gsafety.dawn.community.manage.contract.service;

import com.gsafety.dawn.community.manage.contract.model.ModifyMedicalTreatmentModel;
import com.gsafety.dawn.community.manage.contract.model.refactor.TroubleshootRecord;
import com.gsafety.dawn.community.manage.contract.model.total.DiagnosisCountModel;
import com.gsafety.dawn.community.manage.contract.model.EpidemicPersonModel;
import com.gsafety.dawn.community.manage.contract.model.total.EpidemicTotalStatisticModel;

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

    // 更新医疗情况信息
    boolean modifyMedicalTreatment(ModifyMedicalTreatmentModel medicalTreatmentModel);

    // 统计-总体-分类诊疗意见
    EpidemicTotalStatisticModel overallClassification(String districtCode , String dataSourceId);

    // 统计-总体-医疗情况
    EpidemicTotalStatisticModel overallMedical(String districtCode , String dataSourceId);

    // 统计-小区-分类诊疗意见
    List<EpidemicTotalStatisticModel> plotClassification(String districtCode , String dataSourceId);

    // 统计-小区-医疗情况
    List<EpidemicTotalStatisticModel> plotMedical(String districtCode , String dataSourceId);

    boolean syncTroubleshooting(TroubleshootRecord troubleshootRecord);
}

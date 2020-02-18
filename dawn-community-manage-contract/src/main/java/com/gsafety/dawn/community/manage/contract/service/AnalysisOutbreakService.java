package com.gsafety.dawn.community.manage.contract.service;

import com.gsafety.dawn.community.manage.contract.model.refactor.DailyTroubleshootingStatisticModel;

import java.util.List;

public interface AnalysisOutbreakService {

    // 按社区统计近7日排查人数


    // 确诊、疑似、发热、接触人员近7日统计
    List<DailyTroubleshootingStatisticModel> troubleStatistic();

}

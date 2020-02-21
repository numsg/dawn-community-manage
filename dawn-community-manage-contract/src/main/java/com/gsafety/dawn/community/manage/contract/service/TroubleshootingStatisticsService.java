package com.gsafety.dawn.community.manage.contract.service;

import com.gsafety.dawn.community.manage.contract.model.refactor.DailyTroubleshootingStatisticModel;

import java.util.List;

public interface TroubleshootingStatisticsService {

    List<DailyTroubleshootingStatisticModel> handleHistoryTroubleShootStatistic(String multiTenancy);

}

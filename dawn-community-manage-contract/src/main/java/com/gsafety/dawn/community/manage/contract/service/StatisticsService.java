package com.gsafety.dawn.community.manage.contract.service;

import com.gsafety.dawn.community.manage.contract.model.total.DistributionStatisticsRequest;
import com.gsafety.dawn.community.manage.contract.model.total.DistributionStatisticsResult;

import java.util.List;

public interface StatisticsService {

    /**
     * 社区疫情分布情况统计
     * @param paramModel 参数
     * @return 结果
     */
   List<DistributionStatisticsResult> distributionStatistics(DistributionStatisticsRequest paramModel);
}

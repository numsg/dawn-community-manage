package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.common.util.StringUtil;
import com.gsafety.dawn.community.manage.contract.model.total.DistributionStatisticsRequest;
import com.gsafety.dawn.community.manage.contract.model.total.DistributionStatisticsResult;
import com.gsafety.dawn.community.manage.contract.service.StatisticsService;
import com.gsafety.dawn.community.manage.service.entity.statistics.DistributionStatisticsEntity;
import com.gsafety.dawn.community.manage.service.repository.EpidemicPerStatisticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private EpidemicPerStatisticsRepository epidemicPerStatisticsRepository;

    /**
     * 社区疫情分布情况统计
     *
     * @param paramModel 参数
     * @return 结果
     */
    @Override
    public List<DistributionStatisticsResult> distributionStatistics(DistributionStatisticsRequest paramModel) {
        List<DistributionStatisticsResult> result=new ArrayList<>();
        String  medicalConditionId=paramModel.getMedicalConditionId();
        Date startTime=paramModel.getStartTime();
        Date endTime=paramModel.getEndTime();
        String multiTenancy=paramModel.getMultiTenancy();
        String type = paramModel.getType();
        if (StringUtil.isEmpty(multiTenancy)) {
            return Collections.emptyList();
        }
        List<DistributionStatisticsEntity> queryResult=new ArrayList<>();
        if ("1".equals(type)){ // 按小区统计
            queryResult=epidemicPerStatisticsRepository.statisticsByPlot(medicalConditionId,multiTenancy,startTime,endTime);
        }else if ("2".equals(type)){ // 按性别统计
            queryResult=epidemicPerStatisticsRepository.statisticsByGender(medicalConditionId,multiTenancy,startTime,endTime);
        }else if("3".equals(type)){ //按年龄统计
            queryResult=epidemicPerStatisticsRepository.statisticsByAge(medicalConditionId,multiTenancy,startTime,endTime);
        }

        queryResult.forEach(ele ->{
            DistributionStatisticsResult item=new DistributionStatisticsResult();
            item.setId(ele.getItem());
            item.setValue(ele.getCount().intValue());
            result.add(item);
        });
        return result;
    }
}

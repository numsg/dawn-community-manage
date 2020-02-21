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
import java.util.regex.Pattern;

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
           List<DistributionStatisticsEntity> ageResult= epidemicPerStatisticsRepository.statisticsByAge(medicalConditionId,multiTenancy,startTime,endTime);
            DistributionStatisticsEntity ageGroupOne=new DistributionStatisticsEntity(0L,"30岁以下");
            DistributionStatisticsEntity ageGroupTwo=new DistributionStatisticsEntity(0L,"30-40岁");
            DistributionStatisticsEntity ageGroupThree=new DistributionStatisticsEntity(0L,"40-50岁");
            DistributionStatisticsEntity ageGroupFour=new DistributionStatisticsEntity(0L,"50-60岁");
            DistributionStatisticsEntity ageGroupFive=new DistributionStatisticsEntity(0L,"60岁以上");

            Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");

            ageResult.forEach(entity ->{
                if (pattern.matcher(entity.getItem()).matches()){
                    try {
                        int age = Integer.parseInt(entity.getItem());
                        if (0<= age && age<30){
                            ageGroupOne.setCount( ageGroupOne.getCount()+entity.getCount());
                        }else if (age>=30 && age<40){
                            ageGroupTwo.setCount( ageGroupTwo.getCount()+entity.getCount());
                        }else if(age>=40 && age<50){
                            ageGroupThree.setCount( ageGroupThree.getCount()+entity.getCount());
                        }else if (age>=50 && age <60){
                            ageGroupFour.setCount( ageGroupFour.getCount()+entity.getCount());
                        }else{
                            ageGroupFive.setCount( ageGroupFive.getCount()+entity.getCount());
                        }

                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
            });
            queryResult.add(ageGroupOne);
            queryResult.add(ageGroupTwo);
            queryResult.add(ageGroupThree);
            queryResult.add(ageGroupFour);
            queryResult.add(ageGroupFive);
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

package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.manage.contract.model.refactor.DailyTroubleshootingStatisticModel;
import com.gsafety.dawn.community.manage.contract.service.AnalysisOutbreakService;
import com.gsafety.dawn.community.manage.service.datamappers.refactor.DailyTroubleshootingStatisticMapper;
import com.gsafety.dawn.community.manage.service.entity.refactor.DailyTroubleshootingStatisticEntity;
import com.gsafety.dawn.community.manage.service.repository.EpidemicPersonRepository;
import com.gsafety.dawn.community.manage.service.repository.refactor.TroubleshootHistoryRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * description:
 *
 * @outhor liujian
 * @create 2020-02-18 17:51
 */
@Service
@Transactional
public class AnalysisOutbreakServiceImpl implements AnalysisOutbreakService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EpidemicPersonRepository epidemicPersonRepository;

    @Autowired
    private TroubleshootHistoryRecordRepository troubleshootHistoryRecordRepository;

    @Autowired
    private DailyTroubleshootingStatisticMapper troubleshootingStatisticMapper;

    @Override
    public List<DailyTroubleshootingStatisticModel> troubleStatistic() {
        List<DailyTroubleshootingStatisticEntity> dailyTroubleshootingStatisticEntities = new ArrayList<>();
        dailyTroubleshootingStatisticEntities.addAll(epidemicPersonRepository.statisticHistoryRecordDayMon()) ;
        dailyTroubleshootingStatisticEntities.addAll(epidemicPersonRepository.statisticHistoryRecordTue());
        dailyTroubleshootingStatisticEntities.addAll(epidemicPersonRepository.statisticHistoryRecordDayWed());
        dailyTroubleshootingStatisticEntities.addAll(epidemicPersonRepository.statisticHistoryRecordThu());
        dailyTroubleshootingStatisticEntities.addAll(epidemicPersonRepository.statisticHistoryRecordFri());
        dailyTroubleshootingStatisticEntities.addAll(epidemicPersonRepository.statisticHistoryRecordSat());
        dailyTroubleshootingStatisticEntities.addAll(epidemicPersonRepository.statisticHistoryRecordSun());
        Collections.reverse(dailyTroubleshootingStatisticEntities);
        dailyTroubleshootingStatisticEntities.forEach(a -> {
            char c = a.getDate().charAt(0);
            if("0".charAt(0) == c)
                a.setDate(a.getDate().substring(1));
        });
        return  troubleshootingStatisticMapper.entitiesToModels(dailyTroubleshootingStatisticEntities);
    }
}

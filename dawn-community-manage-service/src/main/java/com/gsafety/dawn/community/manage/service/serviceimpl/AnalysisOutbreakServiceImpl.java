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
import java.util.stream.Stream;

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
    public List<DailyTroubleshootingStatisticModel> troubleStatistic(String multiTenancy) {
        List<DailyTroubleshootingStatisticEntity> dailyTroubleshootingStatisticEntities = new ArrayList<>();
        dailyTroubleshootingStatisticEntities.addAll(epidemicPersonRepository.statisticHistoryRecordDayMon(multiTenancy)) ;
        dailyTroubleshootingStatisticEntities.addAll(epidemicPersonRepository.statisticHistoryRecordTue(multiTenancy));
        dailyTroubleshootingStatisticEntities.addAll(epidemicPersonRepository.statisticHistoryRecordDayWed(multiTenancy));
        dailyTroubleshootingStatisticEntities.addAll(epidemicPersonRepository.statisticHistoryRecordThu(multiTenancy));
        dailyTroubleshootingStatisticEntities.addAll(epidemicPersonRepository.statisticHistoryRecordFri(multiTenancy));
        dailyTroubleshootingStatisticEntities.addAll(epidemicPersonRepository.statisticHistoryRecordSat(multiTenancy));
        dailyTroubleshootingStatisticEntities.addAll(epidemicPersonRepository.statisticHistoryRecordSun(multiTenancy));
        DailyTroubleshootingStatisticEntity before = epidemicPersonRepository.statisticHistoryRecordBefore(multiTenancy).get(0);
        Collections.reverse(dailyTroubleshootingStatisticEntities);
        dailyTroubleshootingStatisticEntities.forEach(a -> {
            char c = a.getDate().charAt(0);
            if("0".charAt(0) == c)
                a.setDate(a.getDate().substring(1));
        });
        for (int i = 0 ; i < dailyTroubleshootingStatisticEntities.size() ; i++){
            DailyTroubleshootingStatisticEntity temp = dailyTroubleshootingStatisticEntities.get(i);
            if(i == 0){
                temp.setAllTotal(before.getTotal() +  temp.getTotal());
                temp.setAllConfirmed(before.getConfirmed() +  temp.getConfirmed());
                temp.setAllContact(before.getContact() + temp.getContact());
                temp.setAllCt(before.getCt() + temp.getCt());
                temp.setAllFever(before.getFever() + temp.getFever());
                temp.setAllSuspect(before.getSuspect() + temp.getSuspect());
                continue;
            }
            DailyTroubleshootingStatisticEntity last = dailyTroubleshootingStatisticEntities.get(i - 1);
            temp.setAllTotal(last.getAllTotal() + temp.getTotal());
            temp.setAllConfirmed(last.getAllConfirmed() + temp.getConfirmed());
            temp.setAllContact(last.getAllContact() + temp.getContact());
            temp.setAllCt(last.getAllCt() + temp.getCt());
            temp.setAllFever(last.getAllFever() + temp.getAllFever());
            temp.setAllSuspect(last.getAllSuspect() + temp.getAllSuspect());
        }
        return  troubleshootingStatisticMapper.entitiesToModels(dailyTroubleshootingStatisticEntities);
    }
}

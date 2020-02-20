package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.manage.contract.model.refactor.DailyTroubleshootingStatisticModel;
import com.gsafety.dawn.community.manage.contract.model.total.DailyTroublePlotStatisticModel;
import com.gsafety.dawn.community.manage.contract.model.total.EpidemicClassificaModel;
import com.gsafety.dawn.community.manage.contract.model.total.TypeOtherModel;
import com.gsafety.dawn.community.manage.contract.model.total.TypePersonModel;
import com.gsafety.dawn.community.manage.contract.service.AnalysisOutbreakService;
import com.gsafety.dawn.community.manage.service.datamappers.refactor.DailyTroubleshootingStatisticMapper;
import com.gsafety.dawn.community.manage.service.entity.refactor.DailyTroublePlotStatisticEntity;
import com.gsafety.dawn.community.manage.service.entity.refactor.DailyTroubleshootingStatisticEntity;
import com.gsafety.dawn.community.manage.service.repository.EpidemicPersonRepository;
import com.gsafety.dawn.community.manage.service.repository.refactor.TroubleshootHistoryRecordRepository;
import com.gsafety.dawn.community.manage.service.serviceimpl.share.DataSourceShareIds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * description:
 *
 * @outhor liujian
 * @create 2020-02-18 17:51
 */
@Service
@Transactional
public class AnalysisOutbreakServiceImpl extends DataSourceShareIds implements AnalysisOutbreakService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EpidemicPersonRepository epidemicPersonRepository;

    @Autowired
    private TroubleshootHistoryRecordRepository troubleshootHistoryRecordRepository;

    @Autowired
    private DailyTroubleshootingStatisticMapper troubleshootingStatisticMapper;


    // 统计图1
    @Override
    public Map<String, List<DailyTroublePlotStatisticModel>> troublePlotStatistic(String multiTenancy, List<String> plots) {

        List<DailyTroublePlotStatisticModel> plotStatisticModels = new ArrayList<>();

        if (CollectionUtils.isEmpty(plots)) {
            for (int i = 0; i < 7; i++) {
                List<DailyTroublePlotStatisticModel> temp = epidemicPersonRepository.staPlotsNull(multiTenancy, i, i - 1);
                plotStatisticModels.addAll(temp);
            }
        } else {
            for (int i = 0; i < 7; i++) {
                List<DailyTroublePlotStatisticModel> temp = epidemicPersonRepository.staPlotsMon(multiTenancy, plots, i, i - 1);
                plotStatisticModels.addAll(temp);
            }
        }
        Collections.reverse(plotStatisticModels);
        Map<String, List<DailyTroublePlotStatisticModel>> collect = plotStatisticModels.stream().collect(Collectors.groupingBy(DailyTroublePlotStatisticModel::getPlot));
        return collect;
    }


    // 统计图er，有问题
    @Override
    public List<DailyTroubleshootingStatisticModel> troubleStatistic(String multiTenancy) {
        List<DailyTroubleshootingStatisticEntity> dailyTroubleshootingStatisticEntities = new ArrayList<>();
        dailyTroubleshootingStatisticEntities.addAll(epidemicPersonRepository.statisticHistoryRecordDayMon(multiTenancy));
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
            if ("0".charAt(0) == c)
                a.setDate(a.getDate().substring(1));
        });
        for (int i = 0; i < dailyTroubleshootingStatisticEntities.size(); i++) {
            DailyTroubleshootingStatisticEntity temp = dailyTroubleshootingStatisticEntities.get(i);
            if (i == 0) {
                temp.setAllTotal(before.getTotal() + temp.getTotal());
                temp.setAllConfirmed(before.getConfirmed() + temp.getConfirmed());
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
        return troubleshootingStatisticMapper.entitiesToModels(dailyTroubleshootingStatisticEntities);
    }

    // 计算每天的增量
    @Override
    public List<DailyTroubleshootingStatisticModel> calcEveryData(String multiTenancy){
        List<DailyTroubleshootingStatisticModel> dailyTroubleshootingStatisticModels = troubleStatisRefor(multiTenancy);
        for(int i = dailyTroubleshootingStatisticModels.size() - 1 ; i>=0 ; i-- ){
            if(i == 7)
                continue;
            DailyTroubleshootingStatisticModel temp = dailyTroubleshootingStatisticModels.get(i);
            DailyTroubleshootingStatisticModel last = dailyTroubleshootingStatisticModels.get(i+1);
            temp.setCt(temp.getAllCt() - last.getAllCt());
            temp.setSuspect(temp.getAllSuspect() - last.getAllSuspect());
            temp.setConfirmed(temp.getAllConfirmed() - last.getAllConfirmed());
            temp.setFever(temp.getAllFever() - last.getAllFever());
            temp.setContact(temp.getAllContact() - last.getAllContact());
        }
        dailyTroubleshootingStatisticModels.remove(7);
        Collections.reverse(dailyTroubleshootingStatisticModels);
        return dailyTroubleshootingStatisticModels;
    }


    // 设计如何求每天的总数
    public List<DailyTroubleshootingStatisticModel> troubleStatisRefor(String multiTenancy) {
        List<DailyTroubleshootingStatisticModel> dailyTroubleshootingStatisticModels = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        for (int i = 0; i < 8; i++) {
            List<TypePersonModel> tempFevers = epidemicPersonRepository.beforeFeversEveryDay(multiTenancy, i);
            List<TypePersonModel> tempContacts = epidemicPersonRepository.beforeContactsEveryDay(multiTenancy, i);
            List<TypeOtherModel> tempConfirmeds = epidemicPersonRepository.beforeOthersEveryDay(CONFIRMEDPATIENTID, multiTenancy, i);
            List<TypeOtherModel> tempSuspects = epidemicPersonRepository.beforeOthersEveryDay(SUSPECTEDPATIENTID, multiTenancy, i);
            List<TypeOtherModel> tempCTs = epidemicPersonRepository.beforeOthersEveryDay(CTDIAGNOSISPNEUMONIAID, multiTenancy, i);
            DailyTroubleshootingStatisticModel dailyTroubleshootingStatisticModel = combineHistoryData(tempFevers, tempContacts, tempConfirmeds, tempSuspects, tempCTs);
            Date date = new Date();
            date.setDate(date.getDate() - i);
            String formatDate = format.format(date);
            dailyTroubleshootingStatisticModel.setDate(formatDate);
            dailyTroubleshootingStatisticModels.add(dailyTroubleshootingStatisticModel);
        }
        return dailyTroubleshootingStatisticModels;
    }


    // 把历史数据组装
    public DailyTroubleshootingStatisticModel combineHistoryData(List<TypePersonModel> tempFevers , List<TypePersonModel> tempContacts , List<TypeOtherModel> tempConfirmeds , List<TypeOtherModel> tempSuspects ,List<TypeOtherModel> tempCTs){
        DailyTroubleshootingStatisticModel dailyTroubleshootingStatisticModel = new DailyTroubleshootingStatisticModel();
        if (CollectionUtils.isEmpty(tempFevers)) {
            dailyTroubleshootingStatisticModel.setAllFever(0);
        } else {
            dailyTroubleshootingStatisticModel.setAllFever(tempFevers.size());
            dailyTroubleshootingStatisticModel.setDate(tempFevers.get(0).getDate());
        }
        if (CollectionUtils.isEmpty(tempContacts)) {
            dailyTroubleshootingStatisticModel.setAllContact(0);
        } else {
            dailyTroubleshootingStatisticModel.setAllContact(tempContacts.size());
            dailyTroubleshootingStatisticModel.setDate(tempContacts.get(0).getDate());
        }
        if (CollectionUtils.isEmpty(tempConfirmeds)) {
            dailyTroubleshootingStatisticModel.setAllConfirmed(0);
        } else {
            dailyTroubleshootingStatisticModel.setAllConfirmed(tempConfirmeds.size());
            dailyTroubleshootingStatisticModel.setDate(tempConfirmeds.get(0).getDate());
        }
        if (CollectionUtils.isEmpty(tempSuspects)) {
            dailyTroubleshootingStatisticModel.setAllSuspect(0);
        } else {
            dailyTroubleshootingStatisticModel.setAllSuspect(tempSuspects.size());
            dailyTroubleshootingStatisticModel.setDate(tempSuspects.get(0).getDate());
        }
        if (CollectionUtils.isEmpty(tempCTs)) {
            dailyTroubleshootingStatisticModel.setAllCt(0);
        } else {
            dailyTroubleshootingStatisticModel.setAllCt(tempCTs.size());
            dailyTroubleshootingStatisticModel.setDate(tempCTs.get(0).getDate());
        }
        return dailyTroubleshootingStatisticModel;
    }


    // 死亡治愈接口 - 添加一个
    @Override
    public List<EpidemicClassificaModel> epidemicCureAndDeath(String multiTenancy){
        List<EpidemicClassificaModel> epidemicClassificaModels = new ArrayList<>();
        for (int i = 0 ; i < 7 ; i++){
            if(i == 0){
                List<EpidemicClassificaModel> todayData = epidemicPersonRepository.statisEpidemicToday(multiTenancy, CURE, DEATH);
                epidemicClassificaModels.addAll(todayData);
                continue;
            }
            List<EpidemicClassificaModel> temp = epidemicPersonRepository.statisEpidemic(multiTenancy, i, i - 1, CURE, DEATH);
            epidemicClassificaModels.addAll(temp);
        }
        Collections.reverse(epidemicClassificaModels);
        List<EpidemicClassificaModel> before = epidemicPersonRepository.statisEpidemicBefore(multiTenancy, CURE, DEATH);
        combineEpideModes(epidemicClassificaModels , before);
        return epidemicClassificaModels;
    }

    // 把结果累加
    public List<EpidemicClassificaModel> combineEpideModes(List<EpidemicClassificaModel> sevenDates , List<EpidemicClassificaModel> beforDates ){
        EpidemicClassificaModel before = beforDates.get(0);
        for(int i= 0 ; i < sevenDates.size() ; i++){
            EpidemicClassificaModel temp = sevenDates.get(i);
            if(i == 0){
                temp.setAllTotal(before.getAllTotal() +  temp.getTotal());
                temp.setAllCure(before.getAllCure() + temp.getCure());
                temp.setAllDeath(before.getAllDeath() + temp.getDeath());
                continue;
            }
            EpidemicClassificaModel last = sevenDates.get(i - 1);
            temp.setAllTotal(last.getAllTotal() + temp.getTotal());
            temp.setAllCure(last.getAllCure() + temp.getCure());
            temp.setAllDeath(last.getAllDeath() + temp.getDeath());
        }
        return sevenDates;
    }
}

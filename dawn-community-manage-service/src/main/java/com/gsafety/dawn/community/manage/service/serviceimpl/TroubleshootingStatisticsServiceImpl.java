package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.manage.contract.model.refactor.DailyTroubleshootingStatisticModel;
import com.gsafety.dawn.community.manage.contract.service.TroubleshootingStatisticsService;
import com.gsafety.dawn.community.manage.service.entity.TroubleshootingStatisticsEntity;
import com.gsafety.dawn.community.manage.service.entity.refactor.TroubleshootHistoryRecordEntity;
import com.gsafety.dawn.community.manage.service.repository.TroubleshootingStatisticsRepository;
import com.gsafety.dawn.community.manage.service.repository.refactor.TroubleshootHistoryRecordRepository;
import com.gsafety.dawn.community.manage.service.serviceimpl.share.DataSourceShareIds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @create 2020-02-20 10:14
 */
@Service
@Transactional
public class TroubleshootingStatisticsServiceImpl extends DataSourceShareIds implements TroubleshootingStatisticsService {

    @Autowired
    TroubleshootHistoryRecordRepository troubleshootHistoryRecordRepository;

    @Autowired
    TroubleshootingStatisticsRepository troubleshootingStatisticsRepository;


    @Override
    public List<DailyTroubleshootingStatisticModel> handleHistoryTroubleShootStatistic(String multiTenancy) {
        // 1.根据multiTenancy查询所有历史统计记录
        List<TroubleshootHistoryRecordEntity> historyRecordEntities = troubleshootHistoryRecordRepository.findAllByMultiTenancyOrderByCreateDate(multiTenancy);
        List<DailyTroubleshootingStatisticModel> result = new ArrayList<>();
        // 2.遍历上述每一条记录，生成统计数据
        for (int i = 0; i < historyRecordEntities.size(); i++) {
            TroubleshootHistoryRecordEntity temp = historyRecordEntities.get(i);

            List<TroubleshootHistoryRecordEntity> hasHistoryRecords = searchHistoryRecords(historyRecordEntities , temp);
            // 查询该条记录是否有历史记录
//            List<TroubleshootHistoryRecordEntity> hasHistoryRecords = troubleshootHistoryRecordRepository.findAllByPersonBaseIdAndIdIsNotOrderByCreateDate(temp.getPersonBaseId(), temp.getId());
            // 如果没有历史记录则直接往统计表修改记录
            if (CollectionUtils.isEmpty(hasHistoryRecords)) {
                insertTroubleStatistic(temp, result);
                continue;
            }
            // 如果有历史记录，则与最后一条历史记录对比
            TroubleshootHistoryRecordEntity lastRecord = hasHistoryRecords.get(hasHistoryRecords.size() - 1);
            // 发热
            int increFever = 0;
            // 最后一条不发热，当前发热
            if (!lastRecord.getIsExceedTemp() && temp.getIsExceedTemp())
                increFever++;
            // 最后一条发热，当前不发热
            if (lastRecord.getIsExceedTemp() && !temp.getIsExceedTemp())
                increFever--;

            // 密接
            int increContact = 0;
            // 最后一条不是密接，当前密接
            if (!lastRecord.getIsContact() && temp.getIsContact())
                increContact++;
            // 最后一条是密接，当前不是密接
            if (lastRecord.getIsContact() && !temp.getIsContact())
                increContact--;

            // 确诊、疑似、CT
            int increConfirmed = 0;
            int increSuspect = 0;
            int increCT = 0;
            // 已设置默认值为暂无 , 故无需判空
            // 确诊
            if (!lastRecord.getMedicalOpinion().equals(CONFIRMEDPATIENTID)
                    && temp.getMedicalOpinion().equals(CONFIRMEDPATIENTID))
                increConfirmed++;
            if (lastRecord.getMedicalOpinion().equals(CONFIRMEDPATIENTID)
                    && !temp.getMedicalOpinion().equals(CONFIRMEDPATIENTID))
                increConfirmed--;
            // 疑似
            if (!lastRecord.getMedicalOpinion().equals(SUSPECTEDPATIENTID)
                    && temp.getMedicalOpinion().equals(SUSPECTEDPATIENTID))
                increSuspect++;
            if (lastRecord.getMedicalOpinion().equals(SUSPECTEDPATIENTID)
                    && !temp.getMedicalOpinion().equals(SUSPECTEDPATIENTID))
                increSuspect--;
            // ct
            if (!lastRecord.getMedicalOpinion().equals(CTDIAGNOSISPNEUMONIAID)
                    && temp.getMedicalOpinion().equals(CTDIAGNOSISPNEUMONIAID))
                increSuspect++;
            if (lastRecord.getMedicalOpinion().equals(CTDIAGNOSISPNEUMONIAID)
                    && !temp.getMedicalOpinion().equals(CTDIAGNOSISPNEUMONIAID))
                increSuspect--;
            // 有历史记录的数据已组装完毕，往统计表插入数据
            historyInsertTroubleStatistic(temp, increFever, increContact, increConfirmed, increSuspect, increCT, result);
        }
        if(CollectionUtils.isEmpty(result))
            return Collections.emptyList();
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        // 处理结果中的时间
        for (int i = 0 ; i < result.size() ; i++){
            DailyTroubleshootingStatisticModel temp = result.get(i);
            String formatDate = format.format(temp.getMeasuringDate());
            temp.setDate(formatDate);
        }
        Date date = new Date();
        date.setDate(date.getDate() - 6);
        if(result.get(result.size() - 1).getMeasuringDate().before(date))
            return Collections.emptyList();

        return completeReal( handleEmptyDate() , result);
    }

    // 构造一个7天的数据
    public List<DailyTroubleshootingStatisticModel> handleEmptyDate(){
        List<DailyTroubleshootingStatisticModel> temp = new ArrayList<>();
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日");
        for (int i = 0 ; i < 7 ; i++){
            Date date = new Date();
            date.setDate(date.getDate() - i);
            String formatDate = format.format(date);
            DailyTroubleshootingStatisticModel dailyTroubleshootingStatisticModel = new DailyTroubleshootingStatisticModel();
            dailyTroubleshootingStatisticModel.setDate(formatDate);
            temp.add(dailyTroubleshootingStatisticModel);
        }
        return temp;
    }

    // 把真实的数据往7的数据补齐
    public List<DailyTroubleshootingStatisticModel> completeReal(List<DailyTroubleshootingStatisticModel> sevenDates ,
                                                                 List<DailyTroubleshootingStatisticModel> realDates){
        List<DailyTroubleshootingStatisticModel> result = new ArrayList<>();
        for (int i = 6 ; i >= 0 ; i--){
            DailyTroubleshootingStatisticModel temp = sevenDates.get(i);
            DailyTroubleshootingStatisticModel isHas = searchByDate(temp.getDate(), realDates);
            if(isHas != null){
                result.add(isHas);
                continue;
            }
            if(isHas == null){
                DailyTroubleshootingStatisticModel last = result.get(result.size() - 1);
                temp.setTotal(0);
                temp.setAllTotal(last.getAllTotal());
                temp.setFever(0);
                temp.setAllFever(last.getAllFever());
                temp.setContact(0);
                temp.setAllContact(last.getAllContact());
                temp.setConfirmed(0);
                temp.setAllConfirmed(last.getAllConfirmed());
                temp.setSuspect(0);
                temp.setAllSuspect(last.getAllSuspect());
                temp.setCt(0);
                temp.setAllCt(last.getAllCt());
                result.add(temp);
                continue;
            }
//            if(isHas == null && i != 0){
//                result.add(temp);
//            }
        }
        return result;
    }

    // 根据时间查找
    public DailyTroubleshootingStatisticModel searchByDate(String date , List<DailyTroubleshootingStatisticModel> realDates){
        DailyTroubleshootingStatisticModel result = null;
        for (int i = 0 ; i < realDates.size() ; i++){
            DailyTroubleshootingStatisticModel temp = realDates.get(i);
            if(temp.getDate().equals(date)){
                result = temp;
                break;
            }
        }
        return result;
    }

    // 原地查找有无历史记录
    public List<TroubleshootHistoryRecordEntity> searchHistoryRecords(List<TroubleshootHistoryRecordEntity> allRecords , TroubleshootHistoryRecordEntity curRecord){
        List<TroubleshootHistoryRecordEntity> result = new ArrayList<>();
        for (int i = 0 ; i < allRecords.size() ; i++){
            TroubleshootHistoryRecordEntity temp = allRecords.get(i);
            if(temp.getPersonBaseId().equals(curRecord.getPersonBaseId()) &&
               !temp.getId().equals(curRecord.getId()))
                result.add(temp);
        }
        return result.stream().sorted(Comparator.comparing(TroubleshootHistoryRecordEntity::getCreateDate)).collect(Collectors.toList());
    }

    public void insertTroubleStatistic(TroubleshootHistoryRecordEntity temp, List<DailyTroubleshootingStatisticModel> result) {
        int increFever = 0;
        if (temp.getIsExceedTemp())
            increFever++;
        int increContact = 0;
        if (temp.getIsContact())
            increContact++;
        int increConfirmed = 0;
        int increSuspect = 0;
        int increCT = 0;
        if (temp.getMedicalOpinion().equals(CONFIRMEDPATIENTID))
            increConfirmed++;
        if (temp.getMedicalOpinion().equals(SUSPECTEDPATIENTID))
            increSuspect++;
        if (temp.getMedicalOpinion().equals(CTDIAGNOSISPNEUMONIAID))
            increCT++;
        historyInsertTroubleStatistic(temp, increFever, increContact, increConfirmed, increSuspect, increCT, result);
    }


    public void historyInsertTroubleStatistic(TroubleshootHistoryRecordEntity temp, int increFever,
                                              int increContact, int increConfirmed,
                                              int increSuspect, int increCT, List<DailyTroubleshootingStatisticModel> result) {
        List<DailyTroubleshootingStatisticModel> records = result.stream().sorted(Comparator.comparing(DailyTroubleshootingStatisticModel::getMeasuringDate)).filter(a -> a.getMeasuringDate().toString().equals(temp.getCreateDate().toString())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(records)) {
            // 如果没有当天已存在的记录 , 则插入新的统计数据
            addNewTroubleStatistic(temp, increFever, increContact, increConfirmed, increSuspect, increCT, result);
        } else {
            // 如果当天已经存在记录，则在当前的记录上修改
            DailyTroubleshootingStatisticModel curStatistic = records.get(records.size() - 1);
            statisticTodayHas(curStatistic, increFever, increContact, increConfirmed, increSuspect, increCT);
        }
    }


    // 判断当前是否有统计数据
    public List<TroubleshootingStatisticsEntity> isHasTroubleStatistic(String plot, String multiTenancy, Date date) {
        return troubleshootingStatisticsRepository.findAllByPlotAndMultiTenancyAndCreateTime(plot, multiTenancy, date);
    }

    // 统计表没有当前人员符合插入的统计记录，则新增统计记录
    public void addNewTroubleStatistic(TroubleshootHistoryRecordEntity temp, int increFever,
                                       int increContact, int increConfirmed,
                                       int increSuspect, int increCT, List<DailyTroubleshootingStatisticModel> result) {
        DailyTroubleshootingStatisticModel curStatistic = new DailyTroubleshootingStatisticModel();
        curStatistic.setDate(temp.getCreateDate().toString());
        curStatistic.setMeasuringDate(temp.getCreateDate());

        curStatistic.setTotal(1);
        curStatistic.setFever(increFever);
        curStatistic.setContact(increContact);
        curStatistic.setConfirmed(increConfirmed);
        curStatistic.setSuspect(increSuspect);
        curStatistic.setCt(increCT);

        if (CollectionUtils.isEmpty(result)) {
            curStatistic.setAllTotal(1);
            curStatistic.setAllFever(increFever);
            curStatistic.setAllContact(increContact);
            curStatistic.setAllConfirmed(increConfirmed);
            curStatistic.setAllSuspect(increSuspect);
            curStatistic.setAllCt(increCT);
        } else {
            DailyTroubleshootingStatisticModel last = result.get(result.size() - 1);
            curStatistic.setAllTotal(1 + last.getAllTotal());
            curStatistic.setAllFever(increFever + last.getAllFever());
            curStatistic.setAllContact(increContact + last.getAllContact());
            curStatistic.setAllConfirmed(increConfirmed + last.getAllConfirmed());
            curStatistic.setAllSuspect(increSuspect + last.getAllSuspect());
            curStatistic.setAllCt(increCT + last.getAllCt());
        }
        result.add(curStatistic);
    }

    // 当天的统计记录已存在，则把该条排查的数据赋值给当前的记录并重新保存
    public void statisticTodayHas(DailyTroubleshootingStatisticModel curStatistic, int increFever,
                                  int increContact, int increConfirmed,
                                  int increSuspect, int increCT) {
        curStatistic.setTotal(curStatistic.getTotal() + 1);
        curStatistic.setAllTotal(curStatistic.getAllTotal() + 1);
        curStatistic.setFever(curStatistic.getFever() + increFever);
        curStatistic.setAllFever(curStatistic.getAllFever() + increFever);
        curStatistic.setContact(curStatistic.getContact() + increContact);
        curStatistic.setAllContact(curStatistic.getAllContact() + increContact);
        curStatistic.setConfirmed(curStatistic.getConfirmed() + increConfirmed);
        curStatistic.setAllConfirmed(curStatistic.getAllConfirmed() + increConfirmed);
        curStatistic.setSuspect(curStatistic.getSuspect() + increSuspect);
        curStatistic.setAllSuspect(curStatistic.getAllSuspect() + increSuspect);
        curStatistic.setCt(curStatistic.getCt() + increCT);
        curStatistic.setAllCt(curStatistic.getAllCt() + increCT);
    }
}

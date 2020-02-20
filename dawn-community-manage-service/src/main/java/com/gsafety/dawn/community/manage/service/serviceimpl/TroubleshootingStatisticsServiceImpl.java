package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.common.util.StringUtil;
import com.gsafety.dawn.community.manage.contract.service.TroubleshootingStatisticsService;
import com.gsafety.dawn.community.manage.service.entity.TroubleshootingStatisticsEntity;
import com.gsafety.dawn.community.manage.service.entity.refactor.TroubleshootHistoryRecordEntity;
import com.gsafety.dawn.community.manage.service.repository.TroubleshootingStatisticsRepository;
import com.gsafety.dawn.community.manage.service.repository.refactor.TroubleshootHistoryRecordRepository;
import com.gsafety.dawn.community.manage.service.serviceimpl.share.DataSourceShareIds;
import fr.opensagres.xdocreport.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
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
    public void handleHistoryTroubleShootStatistic() {
        // 1.查询所有历史排查记录，按create_time排序
        List<TroubleshootHistoryRecordEntity> all = troubleshootHistoryRecordRepository.findAll();
        List<TroubleshootHistoryRecordEntity> collect = all.stream().sorted(Comparator.comparing(TroubleshootHistoryRecordEntity::getCreateTime)).collect(Collectors.toList());
        // 2.遍历上述每一条记录，往统计表插入数据
        for (int i = 0; i < collect.size(); i++) {
            TroubleshootHistoryRecordEntity temp = collect.get(i);
            // 根据当前的小区、multiTenancy、create_Time查询是否有符合要求的记录
            List<TroubleshootingStatisticsEntity> allByPlotAndMultiTenancy = troubleshootingStatisticsRepository.findAllByPlotAndMultiTenancyAndCreateTime(temp.getPlot(), temp.getMultiTenancy(), temp.getCreateDate());
            // 如果没有查询到该小区当天的统计记录，则创建
            if (CollectionUtils.isEmpty(allByPlotAndMultiTenancy)) {


                TroubleshootingStatisticsEntity troubleshootingStatisticsEntity = new TroubleshootingStatisticsEntity();
                troubleshootingStatisticsEntity.setId(StringUtil.genUUID());
                troubleshootingStatisticsEntity.setPlot(temp.getPlot());
                troubleshootingStatisticsEntity.setMultiTenancy(temp.getMultiTenancy());
                troubleshootingStatisticsEntity.setCreateTime(temp.getCreateDate());
                troubleshootingStatisticsEntity.setUpdateTime(temp.getCreateTime());

                long total = 0;
                long fever = 0;
                long contact = 0;
                long confirmed = 0;
                long suspect = 0;
                long ct = 0;
                long allTotal = 0;
                long allFever = 0;
                long allContact = 0;
                long allConfirmed = 0;
                long allSuspect = 0;
                long allCt = 0;

                total++;
                allTotal = total;
                if (temp.getIsExceedTemp()) {
                    fever++;
                    allFever = fever;
                }
                if (temp.getIsContact()) {
                    contact++;
                    allContact++;
                }
                if (!StringUtils.isEmpty(temp.getMedicalOpinion())) {
                    if (temp.getMedicalOpinion().equals(CONFIRMEDPATIENTID)) {
                        confirmed++;
                        allConfirmed++;
                    }
                    if (temp.getMedicalOpinion().equals(SUSPECTEDPATIENTID)) {
                        suspect++;
                        allSuspect++;
                    }
                    if (temp.getMedicalOpinion().equals(CTDIAGNOSISPNEUMONIAID)) {
                        ct++;
                        allCt++;
                    }
                }
                troubleshootingStatisticsEntity.setTotal(total);
                troubleshootingStatisticsEntity.setAllTotal(allTotal);
                troubleshootingStatisticsEntity.setFever(fever);
                troubleshootingStatisticsEntity.setAllFever(allFever);
                troubleshootingStatisticsEntity.setContact(contact);
                troubleshootingStatisticsEntity.setAllContact(allContact);
                troubleshootingStatisticsEntity.setConfirmed(confirmed);
                troubleshootingStatisticsEntity.setAllConfirmed(allConfirmed);
                troubleshootingStatisticsEntity.setSuspect(suspect);
                troubleshootingStatisticsEntity.setAllSuspect(allSuspect);
                troubleshootingStatisticsEntity.setCt(ct);
                troubleshootingStatisticsEntity.setAllCt(allCt);
                troubleshootingStatisticsRepository.save(troubleshootingStatisticsEntity);
            } else {
                // 已经存在统计记录
                TroubleshootingStatisticsEntity have = allByPlotAndMultiTenancy.get(0);
                have.setTotal(have.getTotal()+1);
                have.setAllTotal(have.getAllTotal()+1);
                if (temp.getIsExceedTemp()) {
                    have.setFever(have.getFever() + 1);
                    have.setAllFever(have.getAllFever() + 1);
                }
                if (temp.getIsContact()) {
                    have.setContact(have.getContact() +1);
                    have.setAllContact(have.getAllContact() + 1);
                }
                if (!StringUtils.isEmpty(temp.getMedicalOpinion())) {
                    if (temp.getMedicalOpinion().equals(CONFIRMEDPATIENTID)) {
                        have.setConfirmed(have.getConfirmed() + 1);
                        have.setAllConfirmed(have.getAllConfirmed() + 1);
                    }
                    if (temp.getMedicalOpinion().equals(SUSPECTEDPATIENTID)) {
                        have.setSuspect(have.getSuspect() + 1);
                        have.setAllSuspect(have.getAllSuspect() + 1);
                    }
                    if (temp.getMedicalOpinion().equals(CTDIAGNOSISPNEUMONIAID)) {
                        have.setCt(have.getCt() + 1);
                        have.setAllCt(have.getAllCt() + 1);
                    }
                }
                troubleshootingStatisticsRepository.save(have);
            }
        }
    }
}

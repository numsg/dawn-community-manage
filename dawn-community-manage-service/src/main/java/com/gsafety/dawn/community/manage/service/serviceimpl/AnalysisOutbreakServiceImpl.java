package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.manage.contract.model.refactor.DailyTroubleshootingStatisticModel;
import com.gsafety.dawn.community.manage.contract.service.AnalysisOutbreakService;
import com.gsafety.dawn.community.manage.service.datamappers.refactor.DailyTroubleshootingStatisticMapper;
import com.gsafety.dawn.community.manage.service.repository.refactor.TroubleshootHistoryRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private TroubleshootHistoryRecordRepository troubleshootHistoryRecordRepository;

    @Autowired
    private DailyTroubleshootingStatisticMapper troubleshootingStatisticMapper;

    @Override
    public List<DailyTroubleshootingStatisticModel> troubleStatistic() {
       return troubleshootingStatisticMapper.entitiesToModels(troubleshootHistoryRecordRepository.statisticHistoryRecord());
    }
}

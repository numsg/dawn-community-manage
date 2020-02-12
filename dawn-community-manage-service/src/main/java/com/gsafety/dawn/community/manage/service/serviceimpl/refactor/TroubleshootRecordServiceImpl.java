package com.gsafety.dawn.community.manage.service.serviceimpl.refactor;

import com.gsafety.dawn.community.common.util.CommonUtil;
import com.gsafety.dawn.community.common.util.StringUtil;
import com.gsafety.dawn.community.manage.contract.model.refactor.PlotBuildingUnitStatistics;
import com.gsafety.dawn.community.manage.contract.model.refactor.ReportingStaffStatistics;
import com.gsafety.dawn.community.manage.contract.model.refactor.TroubleshootRecord;
import com.gsafety.dawn.community.manage.contract.service.refactor.TroubleshootRecordService;
import com.gsafety.dawn.community.manage.service.datamappers.refactor.PersonBaseMapper;
import com.gsafety.dawn.community.manage.service.datamappers.refactor.ReportingStaffMapper;
import com.gsafety.dawn.community.manage.service.datamappers.refactor.TroubleshootRecordMapper;
import com.gsafety.dawn.community.manage.service.entity.refactor.*;
import com.gsafety.dawn.community.manage.service.repository.refactor.TroubleshootHistoryRecordRepository;
import com.gsafety.dawn.community.manage.service.repository.refactor.PersonBaseRepository;
import com.gsafety.dawn.community.manage.service.repository.refactor.TroubleshootRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class TroubleshootRecordServiceImpl implements TroubleshootRecordService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PersonBaseRepository personBaseRepository;

    @Autowired
    private TroubleshootRecordRepository troubleshootRecordRepository;

    @Autowired
    private TroubleshootHistoryRecordRepository troubleshootHistoryRecordRepository;

    @Autowired
    private PersonBaseMapper personBaseMapper;

    @Autowired
    private TroubleshootRecordMapper troubleshootRecordMapper;

    @Autowired
    private ReportingStaffMapper reportingStaffMapper;

    @Autowired
    private CommonUtil commonUtil;

    @Override
    public boolean add(TroubleshootRecord troubleshootRecord) {
        try {
            if (troubleshootRecord == null) {
                return false;
            }
            if (troubleshootRecord.getPersonBase() == null) {
                return false;
            }
            if (StringUtil.isEmpty(troubleshootRecord.getPersonBase().getName())) {
                return false;
            }
            if (StringUtil.isEmpty(troubleshootRecord.getPersonBase().getPhone())) {
                return false;
            }
            PersonBaseEntity personBaseEntity = personBaseRepository.findByNameAndPhone(troubleshootRecord.getPersonBase().getName(), troubleshootRecord.getPersonBase().getPhone());
            if (personBaseEntity == null) {
                personBaseEntity = personBaseMapper.modelToEntity(troubleshootRecord.getPersonBase());
                if (!troubleshootRecord.getIsByPhone()) {
                    personBaseEntity.setId(UUID.randomUUID().toString());
                }
                personBaseRepository.save(personBaseEntity);
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            TroubleshootRecordEntity troubleshootRecordEntity = troubleshootRecordRepository.findByPersonBaseId(personBaseEntity.getId());
            troubleshootRecord.setPersonBaseId(personBaseEntity.getId());
            if (troubleshootRecordEntity == null) {
                troubleshootRecordEntity = troubleshootRecordMapper.modelToEntity(troubleshootRecord);
            } else {
                troubleshootRecordEntity = commonUtil.mapper(troubleshootRecordMapper.modelToEntity(troubleshootRecord), troubleshootRecordEntity);
            }
            troubleshootRecordEntity.setCreateDate(format.parse(format.format(troubleshootRecord.getCreateTime())));
            troubleshootRecordEntity.setPersonBase(null);
            troubleshootRecordEntity.setPersonBaseId(personBaseEntity.getId());
            troubleshootRecordRepository.save(troubleshootRecordEntity);
            TroubleshootHistoryRecordEntity troubleshootHistoryRecordEntity = new TroubleshootHistoryRecordEntity();
            troubleshootHistoryRecordEntity = commonUtil.mapper(troubleshootRecordEntity, troubleshootHistoryRecordEntity);
            troubleshootHistoryRecordEntity.setId(UUID.randomUUID().toString());
            troubleshootHistoryRecordEntity.setPersonBase(null);
            troubleshootHistoryRecordEntity.setPersonBaseId(personBaseEntity.getId());
            troubleshootHistoryRecordRepository.save(troubleshootHistoryRecordEntity);
            return troubleshootRecordRepository.getOne(troubleshootRecord.getId()) != null;
        } catch (Exception e) {
            logger.error("add error", e, e.getMessage(), e.getCause());
            return false;
        }
    }

    @Override
    public boolean update(TroubleshootRecord troubleshootRecord) {
        try {
            if (troubleshootRecord == null) {
                return false;
            }
            if (troubleshootRecord.getPersonBase() == null) {
                return false;
            }
            if (StringUtil.isEmpty(troubleshootRecord.getPersonBase().getName())) {
                return false;
            }
            if (StringUtil.isEmpty(troubleshootRecord.getPersonBase().getPhone())) {
                return false;
            }
            PersonBaseEntity personBaseEntity = personBaseRepository.findByNameAndPhone(troubleshootRecord.getPersonBase().getName(), troubleshootRecord.getPersonBase().getPhone());
            if (personBaseEntity != null) {
                personBaseEntity = commonUtil.mapper(troubleshootRecord.getPersonBase(), personBaseEntity);
                personBaseRepository.save(personBaseEntity);
            } else {
                return false;
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            TroubleshootRecordEntity troubleshootRecordEntity = troubleshootRecordRepository.findByPersonBaseId(troubleshootRecord.getPersonBaseId());
            if (troubleshootRecordEntity == null) {
                return false;
            } else {
                troubleshootRecordEntity = commonUtil.mapper(troubleshootRecordMapper.modelToEntity(troubleshootRecord), troubleshootRecordEntity);
            }
            troubleshootRecordEntity.setCreateDate(format.parse(format.format(troubleshootRecord.getCreateTime())));
            troubleshootRecordRepository.save(troubleshootRecordEntity);
            TroubleshootHistoryRecordEntity troubleshootHistoryRecordEntity = new TroubleshootHistoryRecordEntity();
            troubleshootHistoryRecordEntity = commonUtil.mapper(troubleshootRecordEntity, troubleshootHistoryRecordEntity);
            troubleshootHistoryRecordEntity.setId(UUID.randomUUID().toString());
            troubleshootHistoryRecordEntity.setPersonBase(null);
            troubleshootHistoryRecordEntity.setPersonBaseId(troubleshootRecordEntity.getPersonBaseId());
            troubleshootHistoryRecordRepository.save(troubleshootHistoryRecordEntity);
            return troubleshootRecordRepository.getOne(troubleshootRecord.getId()).getCreateTime() == troubleshootRecord.getCreateTime();
        } catch (Exception e) {
            logger.error("update error", e, e.getMessage(), e.getCause());
            return false;
        }
    }

    @Override
    public List<ReportingStaffStatistics> getReportingStaffStatistics(String multiTenancy) {
        try {
            if (StringUtil.isEmpty(multiTenancy)) {
                return Collections.emptyList();
            }
            List<PlotReportingStaffEntity> plotReportingStaffEntities = troubleshootRecordRepository.findPlotReportingStaff(multiTenancy);
            if (CollectionUtils.isEmpty(plotReportingStaffEntities)) {
                return Collections.emptyList();
            }
            return reportingStaffMapper.entitiesToModels(plotReportingStaffEntities);
        } catch (Exception e) {
            logger.error("getReportingStaffStatistics error", e, e.getMessage(), e.getCause());
            return Collections.emptyList();
        }
    }

    @Override
    public List<PlotBuildingUnitStatistics> getPlotBuildingUnitStatistics(String multiTenancy) {
        try {
            if (StringUtil.isEmpty(multiTenancy)) {
                return Collections.emptyList();
            }
            List<PlotBuildingUnitStaffEntity> plotBuildingUnitStaffEntities = troubleshootRecordRepository.findPlotBuildingUnitStaff(multiTenancy);
            if (CollectionUtils.isEmpty(plotBuildingUnitStaffEntities)) {
                return Collections.emptyList();
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            Date date = format.parse(format.format(new Date()));
            List<PlotBuildingUnitStatistics> result = new ArrayList<>();
            Map<String, List<PlotBuildingUnitStaffEntity>> groupPlotBuildingUnitNumbers = plotBuildingUnitStaffEntities.stream().collect(Collectors.groupingBy(PlotBuildingUnitStaffEntity::getPlotBuildingUnitNumber));
            for (Map.Entry<String, List<PlotBuildingUnitStaffEntity>> entry :
                    groupPlotBuildingUnitNumbers.entrySet()) {

                if (!CollectionUtils.isEmpty(entry.getValue())) {
                    PlotBuildingUnitStatistics plotBuildingUnitStatistics = new PlotBuildingUnitStatistics();
                    plotBuildingUnitStatistics.setBuilding(entry.getValue().get(0).getBuilding());
                    plotBuildingUnitStatistics.setPlotId(entry.getValue().get(0).getPlotId());
                    plotBuildingUnitStatistics.setUnitNumber(entry.getValue().get(0).getUnitNumber());
                    Long feverCount = entry.getValue().stream().filter(f -> f.getIsExceedTemp() == true).count();
                    plotBuildingUnitStatistics.setFeverCount(feverCount.intValue());
                    Long checkedCount = entry.getValue().stream().filter(f -> f.getCreateDate().getTime() == date.getTime()).mapToLong(m -> m.getCount()).sum();
                    plotBuildingUnitStatistics.setCheckedCount(checkedCount.intValue());
                    Long unCheckedCount = entry.getValue().stream().filter(f -> f.getCreateDate().getTime() != date.getTime()).mapToLong(m -> m.getCount()).sum();
                    plotBuildingUnitStatistics.setUnCheckedCount(unCheckedCount.intValue());
                    result.add(plotBuildingUnitStatistics);
                }
            }
            return result;
        } catch (Exception e) {
            logger.error("getBuildingUnitStatistics error", e, e.getMessage(), e.getCause());
            return Collections.emptyList();
        }
    }
}

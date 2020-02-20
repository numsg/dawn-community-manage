package com.gsafety.dawn.community.manage.service.serviceimpl.refactor;

import com.gsafety.dawn.community.common.util.CommonUtil;
import com.gsafety.dawn.community.common.util.NumberUtil;
import com.gsafety.dawn.community.common.util.StringUtil;
import com.gsafety.dawn.community.manage.contract.model.refactor.PlotBuildingUnitStatistics;
import com.gsafety.dawn.community.manage.contract.model.refactor.ReportingStaffStatistics;
import com.gsafety.dawn.community.manage.contract.model.refactor.TroubleshootRecord;
import com.gsafety.dawn.community.manage.contract.service.EpidemicPersonService;
import com.gsafety.dawn.community.manage.contract.model.refactor.*;
import com.gsafety.dawn.community.manage.contract.service.refactor.TroubleshootRecordService;
import com.gsafety.dawn.community.manage.service.datamappers.refactor.PersonBaseMapper;
import com.gsafety.dawn.community.manage.service.datamappers.refactor.ReportingStaffMapper;
import com.gsafety.dawn.community.manage.service.datamappers.refactor.TroubleshootRecordMapper;
import com.gsafety.dawn.community.manage.service.entity.DSourceDataEntity;
import com.gsafety.dawn.community.manage.service.entity.DataSourceEntity;
import com.gsafety.dawn.community.manage.service.entity.refactor.*;
import com.gsafety.dawn.community.manage.service.repository.DSourceDataRepository;
import com.gsafety.dawn.community.manage.service.repository.DataSourceRepository;
import com.gsafety.dawn.community.manage.service.repository.refactor.TroubleshootHistoryRecordRepository;
import com.gsafety.dawn.community.manage.service.repository.refactor.PersonBaseRepository;
import com.gsafety.dawn.community.manage.service.repository.refactor.TroubleshootRecordRepository;
import com.gsafety.java.common.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.text.ParseException;
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
    private DataSourceRepository dataSourceRepository;

    @Autowired
    private DSourceDataRepository dSourceDataRepository;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private EpidemicPersonService epidemicPersonService;
    // 分类诊疗医疗意见下的  无
    private static final String NoMedicalOption = "8470e8e9-90ba-484b-8f33-148a1f5028fc";

    // 排查记录历史表保留周期
    @Value("${app.saveCycle}")
    private int saveCycle;

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
                personBaseEntity.setId(UUID.randomUUID().toString());

            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            TroubleshootRecordEntity troubleshootRecordEntity = troubleshootRecordRepository.findByPersonBaseId(personBaseEntity.getId());
            troubleshootRecord.setPersonBaseId(personBaseEntity.getId());
            if (troubleshootRecordEntity == null) {
                troubleshootRecordEntity = troubleshootRecordMapper.modelToEntity(troubleshootRecord);
            } else {
                if (!troubleshootRecordEntity.getId().equals(troubleshootRecord.getId())) {
                    return false;
                }
                troubleshootRecordEntity = commonUtil.mapper(troubleshootRecord, troubleshootRecordEntity);
            }
            personBaseRepository.save(personBaseEntity);
            troubleshootRecordEntity.setCreateDate(format.parse(format.format(troubleshootRecord.getCreateTime())));
            troubleshootRecordEntity.setPersonBase(null);
            troubleshootRecordEntity.setPersonBaseId(personBaseEntity.getId());
            troubleshootRecordRepository.save(troubleshootRecordEntity);
            if (troubleshootHistoryRecordRepository.findCountByPersonBaseId(personBaseEntity.getId()).intValue() == saveCycle) {
                List<String> ids = troubleshootHistoryRecordRepository.findIdByPersonBaseId(personBaseEntity.getId());
                if (!CollectionUtils.isEmpty(ids)) {
                    ids = ids.stream().skip(saveCycle).collect(Collectors.toList());
                    ids.forEach(f -> {
                        troubleshootHistoryRecordRepository.deleteById(f);
                    });
                }
            }
            TroubleshootHistoryRecordEntity troubleshootHistoryRecordEntity = new TroubleshootHistoryRecordEntity();
            troubleshootHistoryRecordEntity = commonUtil.mapper(troubleshootRecordEntity, troubleshootHistoryRecordEntity);
            troubleshootHistoryRecordEntity.setId(UUID.randomUUID().toString());
            troubleshootHistoryRecordEntity.setPersonBase(null);
            troubleshootHistoryRecordEntity.setPersonBaseId(personBaseEntity.getId());
            troubleshootHistoryRecordRepository.save(troubleshootHistoryRecordEntity);

            // 是否新增到重点关注人员
            epidemicPersonService.syncTroubleshooting(troubleshootRecord);
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
            } else {
                return false;
            }
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            TroubleshootRecordEntity troubleshootRecordEntity = troubleshootRecordRepository.findByPersonBaseId(troubleshootRecord.getPersonBaseId());
            if (troubleshootRecordEntity == null) {
                return false;
            } else {
                troubleshootRecordEntity = commonUtil.mapper(troubleshootRecord, troubleshootRecordEntity);
            }
            personBaseRepository.save(personBaseEntity);
            troubleshootRecordEntity.setCreateDate(format.parse(format.format(troubleshootRecord.getCreateTime())));
            troubleshootRecordRepository.save(troubleshootRecordEntity);
            TroubleshootHistoryRecordEntity troubleshootHistoryRecordEntity = new TroubleshootHistoryRecordEntity();
            List<String> existsHistoryId = troubleshootHistoryRecordRepository.findIdByCreateDateAndPersonBaseId(personBaseEntity.getId(), troubleshootRecordEntity.getCreateDate());
            if (!CollectionUtils.isEmpty(existsHistoryId)) {
                troubleshootHistoryRecordEntity = troubleshootHistoryRecordRepository.getOne(existsHistoryId.get(0));
                troubleshootHistoryRecordEntity = commonUtil.mapper(troubleshootRecordEntity, troubleshootHistoryRecordEntity);
            } else {
                troubleshootHistoryRecordEntity = commonUtil.mapper(troubleshootRecordEntity, troubleshootHistoryRecordEntity);
                troubleshootHistoryRecordEntity.setId(UUID.randomUUID().toString());
            }
            troubleshootHistoryRecordEntity.setPersonBase(null);
            troubleshootHistoryRecordEntity.setPersonBaseId(troubleshootRecordEntity.getPersonBaseId());
            troubleshootHistoryRecordRepository.save(troubleshootHistoryRecordEntity);
            // 是否新增到重点关注人员
            epidemicPersonService.syncTroubleshooting(troubleshootRecord);
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

//    @Override
//    public PlotBuildingUnitPagedResult getPlotBuildingUnitStatistics(PagedQueryModel pagedQueryModel) {
//        PlotBuildingUnitPagedResult result = new PlotBuildingUnitPagedResult();
//        String multiTenancy = pagedQueryModel.getMultiTenancy();
//        Sort sort =new Sort(Sort.Direction.ASC, "building").and(new Sort(Sort.Direction.ASC, "unitNumber"));
//        Pageable pageable =PageRequest.of(pagedQueryModel.getPageNumber()-1, pagedQueryModel.getPageSize(),sort);
//
//        try {
//            if (StringUtil.isEmpty(multiTenancy)) {
//                return null;
//            }
//           // Page<PlotBuildingUnitStaffEntity> pageResult = troubleshootRecordRepository.findPlotBuildingUnitStaff(multiTenancy, pageable);
//            //result.setTotal(pageResult.getTotalElements());
//            //List<PlotBuildingUnitStaffEntity> plotBuildingUnitStaffEntities = pageResult.getContent();
//            // List<PlotBuildingUnitStaffEntity> plotBuildingUnitStaffEntities=troubleshootRecordRepository.findPlotBuildingUnitStaff(multiTenancy);
//
//            if (CollectionUtils.isEmpty(plotBuildingUnitStaffEntities)) {
//                return result;
//            }
//            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
//            Date date = format.parse(format.format(new Date()));
//            List<PlotBuildingUnitStatistics> pageContent = new ArrayList<>();
//
//            Map<String, List<PlotBuildingUnitStaffEntity>> groupPlotBuildingUnitNumbers =
//                    plotBuildingUnitStaffEntities.stream().collect(Collectors.groupingBy(PlotBuildingUnitStaffEntity::getPlotBuildingUnitNumber));
//
//            for (Map.Entry<String, List<PlotBuildingUnitStaffEntity>> entry :
//                    groupPlotBuildingUnitNumbers.entrySet()) {
//
//                if (!CollectionUtils.isEmpty(entry.getValue())) {
//                    PlotBuildingUnitStatistics plotBuildingUnitStatistics = new PlotBuildingUnitStatistics();
//                    plotBuildingUnitStatistics.setBuilding(entry.getValue().get(0).getBuilding());
//                    plotBuildingUnitStatistics.setPlotId(entry.getValue().get(0).getPlotId());
//                    Long exceedTempCount = entry.getValue().stream().mapToLong(m -> m.getExceedTempCount()).sum();
//                    plotBuildingUnitStatistics.setUnitNumber(entry.getValue().get(0).getUnitNumber());
//                    plotBuildingUnitStatistics.setFeverCount(exceedTempCount.intValue());
//                    Long checkedCount = entry.getValue().stream().filter(f -> f.getCreateDate().getTime() == date.getTime()).mapToLong(m -> m.getCount()).sum();
//                    plotBuildingUnitStatistics.setCheckedCount(checkedCount.intValue());
//                    Long unCheckedCount = entry.getValue().stream().filter(f -> f.getCreateDate().getTime() != date.getTime()).mapToLong(m -> m.getCount()).sum();
//                    plotBuildingUnitStatistics.setUnCheckedCount(unCheckedCount.intValue());
//                    pageContent.add(plotBuildingUnitStatistics);
//                }
//            }
//            result.setPageContent(pageContent);
//            return result;
//        } catch (Exception e) {
//            logger.error("getBuildingUnitStatistics error", e, e.getMessage(), e.getCause());
//            return null;
//        }
//    }

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

            Map<String, List<PlotBuildingUnitStaffEntity>> groupPlotBuildingUnitNumbers =
                    plotBuildingUnitStaffEntities.stream().collect(Collectors.groupingBy(PlotBuildingUnitStaffEntity::getPlotBuildingUnitNumber));

            for (Map.Entry<String, List<PlotBuildingUnitStaffEntity>> entry :
                    groupPlotBuildingUnitNumbers.entrySet()) {

                if (!CollectionUtils.isEmpty(entry.getValue())) {
                    PlotBuildingUnitStatistics plotBuildingUnitStatistics = new PlotBuildingUnitStatistics();
                    plotBuildingUnitStatistics.setBuilding(entry.getValue().get(0).getBuilding());
                    plotBuildingUnitStatistics.setPlotId(entry.getValue().get(0).getPlotId());
                    Long exceedTempCount = entry.getValue().stream().mapToLong(m -> m.getExceedTempCount()).sum();
                    plotBuildingUnitStatistics.setUnitNumber(entry.getValue().get(0).getUnitNumber());
                    plotBuildingUnitStatistics.setFeverCount(exceedTempCount.intValue());
                    Long checkedCount = entry.getValue().stream().filter(f -> f.getCreateDate().getTime() == date.getTime()).mapToLong(m -> m.getCount()).sum();
                    plotBuildingUnitStatistics.setCheckedCount(checkedCount.intValue());
                    Long unCheckedCount = entry.getValue().stream().filter(f -> f.getCreateDate().getTime() != date.getTime()).mapToLong(m -> m.getCount()).sum();
                    plotBuildingUnitStatistics.setUnCheckedCount(unCheckedCount.intValue());
                    result.add(plotBuildingUnitStatistics);
                }
            }
            return  result.stream().sorted(Comparator.comparing(PlotBuildingUnitStatistics::getBuilding)).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("getBuildingUnitStatistics error", e, e.getMessage(), e.getCause());
            return null;
        }
    }

    @Override
    public CommunityBriefModel getCommunityDailyBriefing(String multiTenancy) {
        CommunityBriefModel result = new CommunityBriefModel();
        List<DataSourceEntity> communities = dataSourceRepository.queryAllByDescription(multiTenancy);
        // 查询的结果正常情况是一个
        if (communities.isEmpty()) {
            return null;
        }
        List<DSourceDataEntity> plots = dSourceDataRepository.findAllByDataSourceId(communities.get(0).getId());
        result.setPlotTotal(plots.size());
        List<PlotBriefModel> plotBriefs = new ArrayList<>();
        Integer troubleshootTotal = 0;
        Integer dailyTroubleshootTotal = 0;
        Integer abnormalTotal = 0;
        for (DSourceDataEntity plot : plots) {
            PlotBriefModel plotBriefModel = new PlotBriefModel();
            Integer plotDailyTroubleshootTotal = 0;
            Integer plotAbnormalTotal = 0;
            String rate = "0.00%";
            List<TroubleshootRecordEntity> records = troubleshootRecordRepository.queryAllByPlotAndMultiTenancy(plot.getId(),multiTenancy);
            Integer plotTroubleshootTotal = records.size();
            if (!records.isEmpty()) {
                // Date date= DateUtil.getDayStartDate();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd 00:00:00");

                try {
                    Date date = format.parse(format.format(new Date()));
                    // 当天上报记录
                    List<TroubleshootRecordEntity> dailyRecords = records.stream().filter(f -> f.getMultiTenancy().equals(multiTenancy) && f.getCreateDate().getTime() == date.getTime()).collect(Collectors.toList());
                    // 当日填报人数
                    plotDailyTroubleshootTotal = dailyRecords.size();
                    if (!dailyRecords.isEmpty()) {
                        // 异常人数
                        plotAbnormalTotal = dailyRecords.stream().filter(f -> f.getIsExceedTemp() ||!f.getMedicalOpinion().equals(NoMedicalOption)).collect(Collectors.toList()).size();
                        //填报率
                        String doubleNumber = NumberUtil.divide(Integer.toString(plotDailyTroubleshootTotal), Integer.toString(plotTroubleshootTotal));
                        rate = NumberUtil.multiply(doubleNumber, Integer.toString(100)) + "%";
                    }
                } catch (ParseException e) {
                    logger.error("getCommunityDailyBriefing error", e, e.getMessage(), e.getCause());

                }
            }
            plotBriefModel.setPlotId(plot.getId());
            plotBriefModel.setPlotName(plot.getName());
            plotBriefModel.setPlotTroubleshootTotal(plotTroubleshootTotal);
            plotBriefModel.setPlotDailyTroubleshootTotal(plotDailyTroubleshootTotal);
            plotBriefModel.setPlotAbnormalTotal(plotAbnormalTotal);
            plotBriefModel.setTroubleshootRate(rate);

            troubleshootTotal += plotTroubleshootTotal;
            dailyTroubleshootTotal += plotDailyTroubleshootTotal;
            abnormalTotal += plotAbnormalTotal;
            plotBriefs.add(plotBriefModel);
        }
        result.setCommunityCode(multiTenancy);
        result.setCommunityName(communities.get(0).getName());
        result.setAbnormalTotal(abnormalTotal);
        result.setDailyTroubleshootTotal(dailyTroubleshootTotal);
        result.setTroubleshootTotal(troubleshootTotal);
        result.setPlotBriefModels(plotBriefs);

        return result;
    }
}

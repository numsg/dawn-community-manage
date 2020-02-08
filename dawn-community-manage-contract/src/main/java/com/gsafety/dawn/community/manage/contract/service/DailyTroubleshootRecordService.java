package com.gsafety.dawn.community.manage.contract.service;

import com.gsafety.dawn.community.manage.contract.model.DailyTroubleshootRecordModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface DailyTroubleshootRecordService {

    // 新增
    DailyTroubleshootRecordModel addDailyRecord(DailyTroubleshootRecordModel dailyTroubleshootRecordModel);

    // 修改
    DailyTroubleshootRecordModel updateDailyRecord(DailyTroubleshootRecordModel dailyTroubleshootRecordModel);

    // 导入日常记录
    List<DailyTroubleshootRecordModel> importTroubleshootRecord(MultipartFile multipartFile);

    // 查询所有按小区分类
    Map<String , List<DailyTroubleshootRecordModel>> queryAll();

    // 按小区过滤
    Map<String , List<DailyTroubleshootRecordModel>> filterByCommunity(List<String> communityIds);

    // 每个小区体温超标 > 37.3 人员
    Map<String , List<DailyTroubleshootRecordModel>> excessiveBodyTemperature();

    // 分页
    List<DailyTroubleshootRecordModel> pagQuery(int skip , int top);

    // 排查每个小区今日登记的人员
    Map<String , List<DailyTroubleshootRecordModel>> registerToda(String startTime , String endTime);
}

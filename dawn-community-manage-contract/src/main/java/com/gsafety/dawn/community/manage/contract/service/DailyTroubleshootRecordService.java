package com.gsafety.dawn.community.manage.contract.service;

import com.gsafety.dawn.community.manage.contract.model.DailyTroubleshootRecordModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DailyTroubleshootRecordService {

    // 新增
    DailyTroubleshootRecordModel addDailyRecord(DailyTroubleshootRecordModel dailyTroubleshootRecordModel);

    // 修改
    DailyTroubleshootRecordModel updateDailyRecord(DailyTroubleshootRecordModel dailyTroubleshootRecordModel);

    // 导入日常记录
    List<DailyTroubleshootRecordModel> importTroubleshootRecord(MultipartFile multipartFile);

}

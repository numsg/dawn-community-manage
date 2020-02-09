package com.gsafety.dawn.community.manage.contract.model.total;

import com.gsafety.dawn.community.manage.contract.model.DailyTroubleshootRecordModel;

import java.util.List;

/**
 * description:
 *
 * @outhor liujian
 * @create 2020-02-10 0:52
 */
public class DailyStatisticPageModel {

    int total;

    List<DailyTroubleshootRecordModel> dailyTroubleshootRecordModels;

    public DailyStatisticPageModel() {
        // 空构造
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DailyTroubleshootRecordModel> getDailyTroubleshootRecordModels() {
        return dailyTroubleshootRecordModels;
    }

    public void setDailyTroubleshootRecordModels(List<DailyTroubleshootRecordModel> dailyTroubleshootRecordModels) {
        this.dailyTroubleshootRecordModels = dailyTroubleshootRecordModels;
    }
}

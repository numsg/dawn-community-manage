package com.gsafety.dawn.community.manage.contract.model;

import java.util.List;

/**
 * description:
 *
 * @outhor liujian
 * @create 2020-02-09 16:47
 */
public class DailyTroubleFilterModel {

    int page;

    int pageSize;

    String keyWord;

    List<Boolean> isFaver;

    // 小区ids
    List<String> plots;

    // 诊断意见
    List<String> medicalOpinion;

    // 当前分组
    DailyStatisticModel dailyStatisticModel;

    public DailyTroubleFilterModel() {
        // 空构造
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public List<Boolean> getIsFaver() {
        return isFaver;
    }

    public void setIsFaver(List<Boolean> isFaver) {
        this.isFaver = isFaver;
    }

    public List<String> getPlots() {
        return plots;
    }

    public void setPlots(List<String> plots) {
        this.plots = plots;
    }

    public List<String> getMedicalOpinion() {
        return medicalOpinion;
    }

    public void setMedicalOpinion(List<String> medicalOpinion) {
        this.medicalOpinion = medicalOpinion;
    }

    public DailyStatisticModel getDailyStatisticModel() {
        return dailyStatisticModel;
    }

    public void setDailyStatisticModel(DailyStatisticModel dailyStatisticModel) {
        this.dailyStatisticModel = dailyStatisticModel;
    }
}

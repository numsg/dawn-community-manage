package com.gsafety.dawn.community.manage.contract.model.refactor;

import java.util.List;

public class CommunityBriefModel {
    private String communityCode;
    private String communityName;
    private Integer plotTotal; // 社区下面的小区数量
    private Integer troubleshootTotal; //应填报总人数
    private Integer dailyTroubleshootTotal;// 当日填报总人数
    private Integer abnormalTotal;//异常人数
    private List<PlotBriefModel> plotBriefModels;

    public String getCommunityCode() {
        return communityCode;
    }

    public void setCommunityCode(String communityCode) {
        this.communityCode = communityCode;
    }

    public Integer getPlotTotal() {
        return plotTotal;
    }

    public void setPlotTotal(Integer plotTotal) {
        this.plotTotal = plotTotal;
    }

    public Integer getTroubleshootTotal() {
        return troubleshootTotal;
    }

    public void setTroubleshootTotal(Integer troubleshootTotal) {
        this.troubleshootTotal = troubleshootTotal;
    }

    public Integer getDailyTroubleshootTotal() {
        return dailyTroubleshootTotal;
    }

    public void setDailyTroubleshootTotal(Integer dailyTroubleshootTotal) {
        this.dailyTroubleshootTotal = dailyTroubleshootTotal;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public Integer getAbnormalTotal() {
        return abnormalTotal;
    }

    public void setAbnormalTotal(Integer abnormalTotal) {
        this.abnormalTotal = abnormalTotal;
    }

    public List<PlotBriefModel> getPlotBriefModels() {
        return plotBriefModels;
    }

    public void setPlotBriefModels(List<PlotBriefModel> plotBriefModels) {
        this.plotBriefModels = plotBriefModels;
    }
}

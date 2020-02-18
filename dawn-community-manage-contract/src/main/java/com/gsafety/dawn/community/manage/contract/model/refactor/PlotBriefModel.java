package com.gsafety.dawn.community.manage.contract.model.refactor;

public class PlotBriefModel {
    private String plotId;
    private String plotName;
    private Integer plotTroubleshootTotal;
    private Integer plotDailyTroubleshootTotal;
    private Integer plotAbnormalTotal;
    private String  troubleshootRate;

    public String getPlotId() {
        return plotId;
    }

    public void setPlotId(String plotId) {
        this.plotId = plotId;
    }

    public String getPlotName() {
        return plotName;
    }

    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }

    public Integer getPlotTroubleshootTotal() {
        return plotTroubleshootTotal;
    }

    public void setPlotTroubleshootTotal(Integer plotTroubleshootTotal) {
        this.plotTroubleshootTotal = plotTroubleshootTotal;
    }

    public Integer getPlotDailyTroubleshootTotal() {
        return plotDailyTroubleshootTotal;
    }

    public void setPlotDailyTroubleshootTotal(Integer plotDailyTroubleshootTotal) {
        this.plotDailyTroubleshootTotal = plotDailyTroubleshootTotal;
    }

    public Integer getPlotAbnormalTotal() {
        return plotAbnormalTotal;
    }

    public void setPlotAbnormalTotal(Integer plotAbnormalTotal) {
        this.plotAbnormalTotal = plotAbnormalTotal;
    }

    public String getTroubleshootRate() {
        return troubleshootRate;
    }

    public void setTroubleshootRate(String troubleshootRate) {
        this.troubleshootRate = troubleshootRate;
    }
}

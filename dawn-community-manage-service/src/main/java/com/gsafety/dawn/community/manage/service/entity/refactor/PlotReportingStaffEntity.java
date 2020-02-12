package com.gsafety.dawn.community.manage.service.entity.refactor;

public class PlotReportingStaffEntity {

    private Long count;
    private String plotId;

    public PlotReportingStaffEntity(Long count, String plotId) {
        this.count = count;
        this.plotId = plotId;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getPlotId() {
        return plotId;
    }

    public void setPlotId(String plotId) {
        this.plotId = plotId;
    }
}

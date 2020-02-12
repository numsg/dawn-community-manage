package com.gsafety.dawn.community.manage.contract.model.refactor;

/**
 * 填报人员统计
 */
public class ReportingStaffStatistics {

    /**
     * 社区id
     */
    private String plotId;
    /**
     * 社区名称
     */
    private String plotName;
    /**
     * 填报人数
     */
    private int count;

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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

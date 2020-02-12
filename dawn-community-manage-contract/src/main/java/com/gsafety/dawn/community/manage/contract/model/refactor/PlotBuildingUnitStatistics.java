package com.gsafety.dawn.community.manage.contract.model.refactor;

/**
 * 楼栋单元分组统计信息
 */
public class PlotBuildingUnitStatistics {

    private String building;
    private String unitNumber;
    private String plotId;
    private int feverCount;
    private int checkedCount;
    private int unCheckedCount;

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public String getPlotId() {
        return plotId;
    }

    public void setPlotId(String plotId) {
        this.plotId = plotId;
    }

    public int getCheckedCount() {
        return checkedCount;
    }

    public void setCheckedCount(int checkedCount) {
        this.checkedCount = checkedCount;
    }

    public int getUnCheckedCount() {
        return unCheckedCount;
    }

    public void setUnCheckedCount(int unCheckedCount) {
        this.unCheckedCount = unCheckedCount;
    }

    public int getFeverCount() {
        return feverCount;
    }

    public void setFeverCount(int feverCount) {
        this.feverCount = feverCount;
    }
}

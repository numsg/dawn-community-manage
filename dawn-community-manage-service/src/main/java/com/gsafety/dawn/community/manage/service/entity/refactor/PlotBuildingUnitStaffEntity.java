package com.gsafety.dawn.community.manage.service.entity.refactor;

import java.util.Date;

public class PlotBuildingUnitStaffEntity {

    private long count;
    private boolean isExceedTemp;
    private String building;
    private String unitNumber;
    private Date createDate;
    private String plotId;
    private String plotBuildingUnitNumber;

    public PlotBuildingUnitStaffEntity(long count, String building, String unitNumber, Date createDate, String plotId, boolean isExceedTemp) {
        this.count = count;
        this.building = building;
        this.unitNumber = unitNumber;
        this.createDate = createDate;
        this.plotId = plotId;
        this.plotBuildingUnitNumber = this.building + "-" + this.unitNumber + "-" + this.plotId;
        this.isExceedTemp = isExceedTemp;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPlotId() {
        return plotId;
    }

    public void setPlotId(String plotId) {
        this.plotId = plotId;
    }

    public String getPlotBuildingUnitNumber() {
        return plotBuildingUnitNumber;
    }

    public void setPlotBuildingUnitNumber(String plotBuildingUnitNumber) {
        this.plotBuildingUnitNumber = plotBuildingUnitNumber;
    }

    public boolean getIsExceedTemp() {
        return this.isExceedTemp;
    }

    public void setIsExceedTemp(boolean isExceedTemp) {
        this.isExceedTemp = isExceedTemp;
    }
}

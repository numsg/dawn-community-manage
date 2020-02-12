package com.gsafety.dawn.community.manage.service.entity.refactor;

import java.util.Date;

public class BuildingUnitStaffEntity {

    private long count;
    private String building;
    private String unitNumber;
    private Date createDate;
    private String plotId;
    private String buildingUnitNumber;

    public BuildingUnitStaffEntity(long count, String building, String unitNumber, Date createDate, String plotId) {
        this.count = count;
        this.building = building;
        this.unitNumber = unitNumber;
        this.createDate = createDate;
        this.plotId = plotId;
        this.buildingUnitNumber = this.building + "-" + this.unitNumber;
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

    public String getBuildingUnitNumber() {
        return buildingUnitNumber;
    }

    public void setBuildingUnitNumber(String buildingUnitNumber) {
        this.buildingUnitNumber = buildingUnitNumber;
    }
}

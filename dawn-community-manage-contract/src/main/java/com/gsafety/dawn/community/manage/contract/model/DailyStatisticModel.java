package com.gsafety.dawn.community.manage.contract.model;

/**
 * description:
 *
 * @outhor liujian
 * @create 2020-02-09 18:57
 */
public class DailyStatisticModel {

    String plotName;

    String plotId;

    String building;

    String unitNumber;

    int feverCount;

    int checked;

    int unchecked;

    public DailyStatisticModel() {
        // 空构造
    }

    public String getPlotName() {
        return plotName;
    }

    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }

    public String getPlotId() {
        return plotId;
    }

    public void setPlotId(String plotId) {
        this.plotId = plotId;
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

    public int getChecked() {
        return checked;
    }

    public void setChecked(int checked) {
        this.checked = checked;
    }

    public int getUnchecked() {
        return unchecked;
    }

    public void setUnchecked(int unchecked) {
        this.unchecked = unchecked;
    }

    public int getFeverCount() {
        return feverCount;
    }

    public void setFeverCount(int feverCount) {
        this.feverCount = feverCount;
    }
}

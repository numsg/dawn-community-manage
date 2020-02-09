package com.gsafety.dawn.community.manage.contract.model.total;

/**
 * description:
 *
 * @outhor liujian
 * @create 2020-02-10 2:44
 */
public class PendingInvestigatModel {

    private String plot;

    private String building;

    private String unitNumber;

    private int page;

    private int pageSize;

    public PendingInvestigatModel() {

    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
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
}

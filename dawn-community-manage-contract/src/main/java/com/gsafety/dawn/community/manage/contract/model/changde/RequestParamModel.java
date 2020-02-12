package com.gsafety.dawn.community.manage.contract.model.changde;

import java.util.Date;

public class RequestParamModel {
    private String currentVillageId;
    private Date startDate;
    private Date endDate;

    public RequestParamModel() {
    }

    public RequestParamModel(String currentVillageId, Date startDate, Date endDate) {
        this.currentVillageId = currentVillageId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCurrentVillageId() {
        return currentVillageId;
    }

    public void setCurrentVillageId(String currentVillageId) {
        this.currentVillageId = currentVillageId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}

package com.gsafety.dawn.community.manage.contract.model.total;

import java.util.Date;

public class DistributionStatisticsRequest {
    private String  medicalConditionId;
    private String type; //统计类型 1-小区，2=性别，3-年龄
    private Date startTime;
    private Date endTime;
    private String multiTenancy;


    public String getMultiTenancy() {
        return multiTenancy;
    }

    public void setMultiTenancy(String multiTenancy) {
        this.multiTenancy = multiTenancy;
    }

    public String getMedicalConditionId() {
        return medicalConditionId;
    }

    public void setMedicalConditionId(String medicalConditionId) {
        this.medicalConditionId = medicalConditionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}

package com.gsafety.dawn.community.manage.contract.model;

import java.util.Date;

/**
 * @create 2020-02-08 12:33
 */
public class DailyTroubleshootRecordModel {

    private String id;

    private String code;

    private String name;

    // 身份证号码
    private String identificationNumber;

    // 性别
    private String sex;

    // phone
    private String phone;

    // 现居住地址
    private String address;

    // 小区
    private String plot;

    // 楼栋
    private String building;

    // 单元号
    private String unitNumber;

    // 房号
    private String roomNo;

    // 体温body temperature
    private String bodyTemperature;

    // 过去14天是否离开过本地区
    private Boolean isLeaveArea;

    // 确诊情况
    private String confirmed_diagnosis;

    // 填报时间
    private Date createTime;

    // 多租户
    private String multiTenancy;

    public DailyTroubleshootRecordModel() {
        // 空构造
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getBodyTemperature() {
        return bodyTemperature;
    }

    public void setBodyTemperature(String bodyTemperature) {
        this.bodyTemperature = bodyTemperature;
    }

    public Boolean getLeaveArea() {
        return isLeaveArea;
    }

    public void setLeaveArea(Boolean leaveArea) {
        isLeaveArea = leaveArea;
    }

    public String getConfirmed_diagnosis() {
        return confirmed_diagnosis;
    }

    public void setConfirmed_diagnosis(String confirmed_diagnosis) {
        this.confirmed_diagnosis = confirmed_diagnosis;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMultiTenancy() {
        return multiTenancy;
    }

    public void setMultiTenancy(String multiTenancy) {
        this.multiTenancy = multiTenancy;
    }
}




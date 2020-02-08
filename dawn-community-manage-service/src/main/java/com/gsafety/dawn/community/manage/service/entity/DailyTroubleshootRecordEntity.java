package com.gsafety.dawn.community.manage.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @create 2020-02-08 12:17
 */
@Entity
@Table(name = "b_daily_troubleshoot_record")
public class DailyTroubleshootRecordEntity {

    @Id
    @Column(name = "id",length = 64,nullable = false)
    private String id;

    @Column(name = "code",length = 64,nullable = false)
    private String code;

    @Column(name = "name",length = 128,nullable = false)
    private String name;

    // 身份证号码
    @Column(name = "identification_number",length = 64)
    private String identificationNumber;

    // 性别
    @Column(name = "sex",length = 64,nullable = false)
    private String sex;

    // phone
    @Column(name = "phone",length = 64,nullable = false)
    private String phone;

    // 现居住地址
    @Column(name = "address",length = 128,nullable = false)
    private String address;

    // 小区
    @Column(name = "plot",length = 128)
    private String plot;

    // 楼栋
    @Column(name = "building",length = 128)
    private String building;

    // 单元号
    @Column(name = "unit_number",length = 128)
    private String unitNumber;

    // 房号
    @Column(name = "room_no",length = 128)
    private String roomNo;

    // 体温body temperature
    @Column(name = "body_temperature",length = 128)
    private String bodyTemperature;

    // 过去14天是否离开过本地区
    @Column(name = "whether_leave_area",length = 128)
    private Boolean isLeaveArea;

    // 确诊情况
    @Column(name = "confirmed_diagnosis",length = 128)
    private String confirmed_diagnosis;

    // 填报时间
    @Column(name = "create_time",length = 128)
    private String createTime;

    // 多租户
    @Column(name="multi_tenancy")
    private String multiTenancy;

    public DailyTroubleshootRecordEntity() {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getMultiTenancy() {
        return multiTenancy;
    }

    public void setMultiTenancy(String multiTenancy) {
        this.multiTenancy = multiTenancy;
    }
}

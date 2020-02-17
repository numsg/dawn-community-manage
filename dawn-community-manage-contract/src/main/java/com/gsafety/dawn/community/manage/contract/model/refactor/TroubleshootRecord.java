package com.gsafety.dawn.community.manage.contract.model.refactor;

import javax.persistence.Column;
import java.util.Date;

public class TroubleshootRecord {

    private String id;

    private String personBaseId;

    private PersonBase personBase;

    // 小区
    private String plot;

    // 楼栋
    private String building;

    // 单元号
    private String unitNumber;

    // 房号
    private String roomNo;

    // 年龄
    private int age;

    // 过去14天是否离开过本地区
    private boolean isLeaveArea;

    // 确诊情况
    private String confirmed_diagnosis;

    // 填报时间
    private Date createTime;

    /**
     * 排查日期
     */
    private Date createDate;

    // 多租户
    private String multiTenancy;

    // 体温是否大于37.3
    private boolean isExceedTemp;

    // 是否有接触史
    private boolean isContact;

    // 其它症状
    private String otherSymptoms;

    // 医疗意见
    private String medicalOpinion;

    // 备注
    private String note;

    /**
     * 数据是否通过移动端
     */
    private boolean isByPhone;

    /**
     * 行政区划code
     */
    private String districtCode;


    //责任人名称
    @Column(name = "reporter_name", length = 128)
    private String reporterName;
    //责任人电话
    @Column(name = "reporter_phone", length = 15)
    private String reporterPhone;
    // 拓展字段
    @Column(name = "expend_property",length = 10240)
    private String expendProperty;

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName;
    }

    public String getReporterPhone() {
        return reporterPhone;
    }

    public void setReporterPhone(String reporterPhone) {
        this.reporterPhone = reporterPhone;
    }

    public String getExpendProperty() {
        return expendProperty;
    }

    public void setExpendProperty(String expendProperty) {
        this.expendProperty = expendProperty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonBaseId() {
        return personBaseId;
    }

    public void setPersonBaseId(String personBaseId) {
        this.personBaseId = personBaseId;
    }

    public PersonBase getPersonBase() {
        return personBase;
    }

    public void setPersonBase(PersonBase personBase) {
        this.personBase = personBase;
    }

    public boolean getIsLeaveArea() {
        return this.isLeaveArea;
    }

    public void setIsLeaveArea(boolean isLeaveArea) {
        this.isLeaveArea = isLeaveArea;
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

    public boolean getIsExceedTemp() {
        return this.isExceedTemp;
    }

    public void setIsExceedTemp(boolean isExceedTemp) {
        this.isExceedTemp = isExceedTemp;
    }

    public boolean getIsContact() {
        return this.isContact;
    }

    public void setIsContact(boolean isContact) {
        this.isContact = isContact;
    }

    public String getOtherSymptoms() {
        return otherSymptoms;
    }

    public void setOtherSymptoms(String otherSymptoms) {
        this.otherSymptoms = otherSymptoms;
    }

    public String getMedicalOpinion() {
        return medicalOpinion;
    }

    public void setMedicalOpinion(String medicalOpinion) {
        this.medicalOpinion = medicalOpinion;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getIsByPhone() {
        return this.isByPhone;
    }

    public void setIsByPhone(boolean isByPhone) {
        this.isByPhone = isByPhone;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }
}

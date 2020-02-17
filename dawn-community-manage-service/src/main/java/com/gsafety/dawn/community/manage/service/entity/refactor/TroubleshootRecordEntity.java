package com.gsafety.dawn.community.manage.service.entity.refactor;

import javax.persistence.*;
import java.util.Date;

/**
 * 最新的排查记录
 */
@Entity
@Table(name = "b_troubleshoot_record")
public class TroubleshootRecordEntity {

    @Id
    @Column(name = "id", length = 64, nullable = false)
    private String id;

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

    // 年龄
    @Column(name="age")
    private int age;

    // 过去14天是否离开过本地区
    @Column(name = "whether_leave_area", length = 128)
    private Boolean isLeaveArea;

    // 确诊情况
    @Column(name = "confirmed_diagnosis", length = 128)
    private String confirmed_diagnosis;

    // 填报时间
    @Column(name = "create_time", length = 128)
    private Date createTime;

    @Column(name = "b_person_base_id", length = 64)
    private String personBaseId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_person_base_id", nullable = false, insertable = false, updatable = false)
    private PersonBaseEntity personBase;

    /**
     * 排查日期
     */
    @Column(name = "create_date", length = 128)
    private Date createDate;

    // 多租户
    @Column(name = "multi_tenancy")
    private String multiTenancy;

    /** 体温记录情况
     * a:小于36℃
     * b:36-36.5℃
     * c:36.5-37℃
     * d:37-37.3℃
     * e:>37.3℃
     */
    @Column(name = "is_exceed_temp",length = 128)
    private String isExceedTemp;

    // 是否有接触史
    @Column(name = "is_contact")
    private boolean isContact;

    // 其它症状
    @Column(name = "other_symptoms", length = 10240)
    private String otherSymptoms;

    // 医疗意见
    @Column(name = "medical_opinion", length = 10240)
    private String medicalOpinion;

    // 备注
    @Column(name = "note", length = 10240)
    private String note;

    /**
     * 数据是否通过移动端
     */
    @Column(name = "is_by_phone")
    private boolean isByPhone;

    /**
     * 行政区划code
     */
    @Column(name = "district_code", length = 128)
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getIsLeaveArea() {
        return this.isLeaveArea;
    }

    public void setIsLeaveArea(Boolean isLeaveArea) {
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMultiTenancy() {
        return multiTenancy;
    }

    public void setMultiTenancy(String multiTenancy) {
        this.multiTenancy = multiTenancy;
    }



    public boolean getIsContact() {
        return this.isContact;
    }

    public void setIsContact(boolean isContact) {
        this.isContact = isContact;
    }

    public String getIsExceedTemp() {
        return isExceedTemp;
    }

    public void setIsExceedTemp(String isExceedTemp) {
        this.isExceedTemp = isExceedTemp;
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

    public String getPersonBaseId() {
        return personBaseId;
    }

    public void setPersonBaseId(String personBaseId) {
        this.personBaseId = personBaseId;
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

    public PersonBaseEntity getPersonBase() {
        return personBase;
    }

    public void setPersonBase(PersonBaseEntity personBase) {
        this.personBase = personBase;
    }

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
}

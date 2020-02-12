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

    @Column(name = "person_base_id", length = 64, nullable = false)
    private String personBaseId;
    /**
     * 排查日期
     */
    @Column(name = "create_date", length = 128)
    private Date createDate;

    // 多租户
    @Column(name = "multi_tenancy")
    private String multiTenancy;

    // 体温是否大于37.3
    @Column(name = "is_exceed_temp")
    private boolean isExceedTemp;

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
    @Column(name = "district_code", length = 36)
    private String districtCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public boolean isExceedTemp() {
        return isExceedTemp;
    }

    public void setExceedTemp(boolean exceedTemp) {
        isExceedTemp = exceedTemp;
    }

    public boolean isContact() {
        return isContact;
    }

    public void setContact(boolean contact) {
        isContact = contact;
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
}

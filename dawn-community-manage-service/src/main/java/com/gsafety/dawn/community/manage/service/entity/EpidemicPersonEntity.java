package com.gsafety.dawn.community.manage.service.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "b_epidemic_person")
public class EpidemicPersonEntity {
    @Id
    @Column(name = "id", length = 64, nullable = false)
    private String id;

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Column(name = "age", length = 3)
    private Integer age;

    // 性别 数据字典中的性别
    @Column(name = "gender", length = 64)
    private String gender;

    @Column(name = "mobile_number", length = 11)
    private String mobileNumber;

    // 小区id
    @Column(name = "village_id", length = 64)
    private String villageId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "village_id", nullable = false,insertable=false,updatable = false)
//    private DSourceDataEntity dSourceDataEntity;

    // 体温
    @Column(name = "temperature", length = 12)
    private boolean temperature;

    // 确诊情况
    @Column(name = "diagnosis_situation", length = 64)
    private String diagnosisSituation;

    // 医疗情况
    @Column(name = "medical_condition", length = 64)
    private String medicalCondition;

    // 特殊情况
    @Column(name = "special_situation", length = 64)
    private String specialSituation;

    // 创建时间
    @Column(name = "submit_time", nullable = false)
    private Date submitTime;

    @Column(name = "note", length = 2000)
    private String note;

    // 更新就医情况时间
    @Column(name = "disease_time")
    private Date diseaseTime;

    // 更新基本信息时间
    @Column(name = "update_time")
    private Date updateTime;

    // 多租户
    @Column(name = "multi_tenancy")
    private String multiTenancy;

    // 拓展信息
    @Column(name = "expend_property")
    private String expendProperty;

    // + 是否有接触历史
    @Column(name = "is_contact")
    private boolean isContact;
    // + 确诊情况--数据字典(分类诊疗意见)
    @Column(name = "confirmed_diagnosis" , length = 64)
    private String confirmedDiagnosis;
    // + 预留字段重点人员的计算逻辑
    @Column(name = "reserve_field" , length = 1024)
    private String reserveField;
    // + 是否来源于手机端
    @Column(name = "is_by_phone")
    private boolean isByPhone;
    // + 操作人
    @Column(name = "operator")
    private String operator;
    // + 楼栋
    @Column(name = "building")
    private String building;
    // + 单元
    @Column(name = "unit_number")
    private String unitNumber;
    // + 房间号
    @Column(name = "room_number")
    private String roomNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId;
    }

    public boolean isTemperature() {
        return temperature;
    }

    public void setTemperature(boolean temperature) {
        this.temperature = temperature;
    }

    public String getDiagnosisSituation() {
        return diagnosisSituation;
    }

    public void setDiagnosisSituation(String diagnosisSituation) {
        this.diagnosisSituation = diagnosisSituation;
    }

    public String getMedicalCondition() {
        return medicalCondition;
    }

    public void setMedicalCondition(String medicalCondition) {
        this.medicalCondition = medicalCondition;
    }

    public String getSpecialSituation() {
        return specialSituation;
    }

    public void setSpecialSituation(String specialSituation) {
        this.specialSituation = specialSituation;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDiseaseTime() {
        return diseaseTime;
    }

    public void setDiseaseTime(Date diseaseTime) {
        this.diseaseTime = diseaseTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMultiTenancy() {
        return multiTenancy;
    }

    public void setMultiTenancy(String multiTenancy) {
        this.multiTenancy = multiTenancy;
    }

    public String getExpendProperty() {
        return expendProperty;
    }

    public void setExpendProperty(String expendProperty) {
        this.expendProperty = expendProperty;
    }

    public boolean isContact() {
        return isContact;
    }

    public void setContact(boolean contact) {
        isContact = contact;
    }

    public String getConfirmedDiagnosis() {
        return confirmedDiagnosis;
    }

    public void setConfirmedDiagnosis(String confirmedDiagnosis) {
        this.confirmedDiagnosis = confirmedDiagnosis;
    }

    public String getReserveField() {
        return reserveField;
    }

    public void setReserveField(String reserveField) {
        this.reserveField = reserveField;
    }

    public boolean isByPhone() {
        return isByPhone;
    }

    public void setByPhone(boolean byPhone) {
        isByPhone = byPhone;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}

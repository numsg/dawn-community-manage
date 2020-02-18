package com.gsafety.dawn.community.manage.contract.model;

import java.util.Date;

public class EpidemicPersonModel {
    private String id;
    private String name;
    private Integer age;
    private String gender;
    private String mobileNumber;
    private String villageId;
    private boolean temperature;
    private String diagnosisSituation;
    private String medicalCondition;
    private String specialSituation;
    private Date submitTime;
    private Date diseaseTime;
    private Date updateTime;
    private String note;
    private String multiTenancy;
    private String expendProperty;
    // + 是否接触
    private boolean isContact;
    // + 确诊情况--数据字典(分类诊疗意见)
    private String confirmedDiagnosis;
    // + 预留字段重点人员的计算逻辑
    private String reserveField;
    // + 是否来源于手机端
    private boolean isByphone;
    // + 操作人
    private String operator;
    // + 楼栋
    private String building;
    // + 单元
    private String unitNumber;
    // + 房间号
    private String roomNumber;


    public EpidemicPersonModel() {
        //无参构造
    }

    public EpidemicPersonModel(String id, String name, Integer age, String gender, String mobileNumber, String villageId, boolean temperature, String diagnosisSituation, String medicalCondition, String specialSituation, Date submitTime, Date diseaseTime, Date updateTime, String note, String multiTenancy, String expendProperty) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.villageId = villageId;
        this.temperature = temperature;
        this.diagnosisSituation = diagnosisSituation;
        this.medicalCondition = medicalCondition;
        this.specialSituation = specialSituation;
        this.submitTime = submitTime;
        this.diseaseTime = diseaseTime;
        this.updateTime = updateTime;
        this.note = note;
        this.multiTenancy = multiTenancy;
        this.expendProperty = expendProperty;
    }

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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    public boolean isByphone() {
        return isByphone;
    }

    public void setByphone(boolean byphone) {
        isByphone = byphone;
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

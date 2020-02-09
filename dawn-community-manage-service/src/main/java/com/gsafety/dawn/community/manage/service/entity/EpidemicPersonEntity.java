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

    @Column(name = "gender", length = 64 )
    private String gender;

    @Column(name = "mobile_number", length = 11 )
    private String mobileNumber;

    @Column(name = "village_id", length = 64)
    private String villageId;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "village_id", nullable = false,insertable=false,updatable = false)
//    private DSourceDataEntity dSourceDataEntity;

    @Column(name = "temperature", length = 6)
    private String temperature;

    @Column(name = "diagnosis_situation", length = 64)
    private String diagnosisSituation;

    @Column(name = "medical_condition", length = 64)
    private String medicalCondition;

    @Column(name = "special_situation", length = 64)
    private String specialSituation;

    @Column(name = "submit_time",nullable = false)
    private Date submitTime;

    @Column(name = "note", length = 2000)
    private String note;

    @Column(name = "disease_time")
    private Date diseaseTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "multi_tenancy")
    private String multiTenancy;

    @Column(name = "expend_property")
    private String expendProperty;

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

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
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
}

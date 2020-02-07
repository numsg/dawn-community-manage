package com.gsafety.dawn.community.manage.contract.model;

import java.util.Date;

public class EpidemicPersonModel {
    private String id;
    private String name;
    private String gender;
    private String address;
    private String district;
    private String medicalCondition;
    private String specialSituation;
    private Date submitTime;
    private String note;
    private Date diseaseTime;
    private String multiTenancy;
    private String expendProperty;

    public EpidemicPersonModel() {
        //无参构造
    }

    public EpidemicPersonModel(String id, String name, String gender, String address, String district, String medicalCondition, String specialSituation, Date submitTime, String note, Date diseaseTime, String multiTenancy, String expendProperty) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.district = district;
        this.medicalCondition = medicalCondition;
        this.specialSituation = specialSituation;
        this.submitTime = submitTime;
        this.note = note;
        this.diseaseTime = diseaseTime;
        this.multiTenancy = multiTenancy;
        this.expendProperty = expendProperty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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

    public void setDiseaseTime(Date diseaseTime) {
        this.diseaseTime = diseaseTime;
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

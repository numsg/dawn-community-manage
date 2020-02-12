package com.gsafety.dawn.community.manage.contract.model.refactor;

import java.util.List;

public class PersonBase {

    private String id;

    private String code;

    // name
    private String name;

    // 电话
    private String phone;

    // 性别
    private String sex;

    // 住址
    private String address;

    // 身份证号码
    private String identificationNumber;

    // other
    private String other;

    private String multiTenancy;

    /**
     * 最新的排查记录id
     */
    private String troubleshootRecordId;

    /**
     * 最新的排查记录
     */
    private TroubleshootRecord troubleshootRecord;

    /**
     * 数据是否通过移动端
     */
    private boolean isByPhone;

    /**
     * 行政区划code
     */
    private String districtCode;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getMultiTenancy() {
        return multiTenancy;
    }

    public void setMultiTenancy(String multiTenancy) {
        this.multiTenancy = multiTenancy;
    }

    public String getTroubleshootRecordId() {
        return troubleshootRecordId;
    }

    public void setTroubleshootRecordId(String troubleshootRecordId) {
        this.troubleshootRecordId = troubleshootRecordId;
    }

    public TroubleshootRecord getTroubleshootRecord() {
        return troubleshootRecord;
    }

    public void setTroubleshootRecord(TroubleshootRecord troubleshootRecord) {
        this.troubleshootRecord = troubleshootRecord;
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

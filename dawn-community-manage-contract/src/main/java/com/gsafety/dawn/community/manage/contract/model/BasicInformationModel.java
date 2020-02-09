package com.gsafety.dawn.community.manage.contract.model;


/**
 * description:
 *
 * @outhor liujian
 * @create 2020-02-09 16:03
 */
public class BasicInformationModel {

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

    public BasicInformationModel() {
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
}

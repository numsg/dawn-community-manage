package com.gsafety.dawn.community.manage.service.entity.refactor;

import javax.persistence.*;

@Entity
@Table(name = "b_person_base")
public class PersonBaseEntity {

    @Id
    @Column(name = "id", length = 64, nullable = false)
    private String id;

    @Column(name = "code", length = 64)
    private String code;

    // name
    @Column(name = "name", length = 64, nullable = false)
    private String name;

    // 电话
    @Column(name = "phone", length = 64, nullable = false)
    private String phone;

    // 性别
    @Column(name = "sex", length = 64, nullable = false)
    private String sex;

    // 住址
    @Column(name = "address", length = 256)
    private String address;

    // 身份证号码
    @Column(name = "identification_number", length = 64)
    private String identificationNumber;

    // other
    @Column(name = "other", length = 10024)
    private String other;

    @Column(name = "multi_tenancy")
    private String multiTenancy;

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

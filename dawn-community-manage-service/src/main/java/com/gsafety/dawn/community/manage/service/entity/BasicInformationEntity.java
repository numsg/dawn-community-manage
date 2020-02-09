package com.gsafety.dawn.community.manage.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * description:
 *
 * @outhor liujian
 * @create 2020-02-09 15:43
 */
@Entity
@Table(name = "b_basic_information")
public class BasicInformationEntity {

    @Id
    @Column(name = "id",length = 64,nullable = false)
    private String id;

    @Column(name = "code",length = 64,nullable = false)
    private String code;

    // name
    @Column(name = "name",length = 64,nullable = false)
    private String name;

    // 电话
    @Column(name = "phone",length = 64,nullable = false)
    private String phone;

    // 性别
    @Column(name = "sex",length = 64,nullable = false)
    private String sex;

    // 住址
    @Column(name = "address",length = 256,nullable = false)
    private String address;

    // 身份证号码
    @Column(name = "identification_number",length = 64)
    private String identificationNumber;

    // other
    @Column(name = "other",length = 10024)
    private String other;

    public BasicInformationEntity() {
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

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
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

}

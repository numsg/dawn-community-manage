package com.gsafety.dawn.community.manage.service.entity;

import org.springframework.boot.SpringApplication;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "b_access_control")
public class AccessControlEntity {
    @Id
    @Column(name = "id", length = 64, nullable = false)
    private String id;

    @Column(name = "name", length = 128, nullable = false)
    private String name;

    @Column(name = "gender", length = 1, nullable = false)
    private String gender;//性别

    @Column(name = "mobileNumber", length = 11, nullable = false)
    private Integer mobileNumber;//手机号

    @Column(name = "roomNumber", length = 10, nullable = false)
    private String roomNumber;//房号

    @Column(name = "type", length = 1, nullable = false)
    private String type;// 类型：出/入

    @Column(name = "way", length = 2, nullable = false)
    private String way; // 方式：步行/自驾

    @Column(name = "reason", length = 2000, nullable = false)
    private String reason;//原因

    @Column(name = "note", length = 2000)
    private String note;//备注

    @Column(name = "temperature", length = 6, nullable = false)
    private String temperature;// 温度

    @Column(name = "time", nullable = false)
    private Date time; //时间

    @Column(name = "multi_tenancy")
    private String multiTenancy; // 多租户

    @Column(name = "expend_property")
    private String expendProperty;// 拓展属性

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

    public Integer getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(Integer mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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

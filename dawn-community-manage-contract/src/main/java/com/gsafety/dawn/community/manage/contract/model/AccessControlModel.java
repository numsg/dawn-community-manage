package com.gsafety.dawn.community.manage.contract.model;

import java.util.Date;

public class AccessControlModel {
    private String id;
    private String name;
    private String gender;//性别
    private Integer mobileNumber;//手机号
    private String roomNumber;//房号
    private String type;// 类型：出/入
    private String way; // 方式：步行/自驾
    private String reason;//原因
    private String note;//备注
    private String temperature;// 温度
    private Date time; //时间
    private String multiTenancy; // 多租户
    private String expendProperty;// 拓展属性

    public AccessControlModel() {
        // 无参构造
    }

    public AccessControlModel(String id, String name, String gender, Integer mobileNumber, String roomNumber, String type, String way, String reason, String note, String temperature, Date time, String multiTenancy, String expendProperty) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.roomNumber = roomNumber;
        this.type = type;
        this.way = way;
        this.reason = reason;
        this.note = note;
        this.temperature = temperature;
        this.time = time;
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

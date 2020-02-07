package com.gsafety.dawn.community.manage.contract.model;

import java.util.Date;

public class SystemSetModel {
    private String id;

    private String code;

    private String systemName;

    private String systemLogo;

    private Date updateTime;

    private String updateUser;

    private String multiTenancy;

    public SystemSetModel() {
        //无参构造
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

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getSystemLogo() {
        return systemLogo;
    }

    public void setSystemLogo(String systemLogo) {
        this.systemLogo = systemLogo;
    }


    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public String getMultiTenancy() {
        return multiTenancy;
    }

    public void setMultiTenancy(String multiTenancy) {
        this.multiTenancy = multiTenancy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "SystemSetModel{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", systemName='" + systemName + '\'' +
                ", systemLogo='" + systemLogo + '\'' +
                ", updateTime=" + updateTime +
                ", updateUser='" + updateUser + '\'' +
                ", multiTenancy='" + multiTenancy + '\'' +
                '}';
    }
}

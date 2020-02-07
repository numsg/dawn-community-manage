package com.gsafety.dawn.community.manage.service.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "b_system_set")
public class SystemSetEntity {
    @Id
    @Column(name = "id",length = 64,nullable = false)
    private String id;

    @Column(name = "code",length = 128,nullable = false)
    private String code;

    @Column(name = "name",length = 1024,nullable = false)
    private String systemName;

    @Column(name = "logo",nullable = false)
    private String systemLogo;

    @Column(name = "update_time",nullable = false)
    private Date updateTime;

    @Column(name = "update_person")
    private String updateUser;

    @Column(name = "multi_tenancy")
    private String multiTenancy;

    public SystemSetEntity() {
        // 无参构造
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

    public void setSystemLogo(String systemLog) {
        this.systemLogo = systemLog;
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
        return "SystemSetEntity{" +
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

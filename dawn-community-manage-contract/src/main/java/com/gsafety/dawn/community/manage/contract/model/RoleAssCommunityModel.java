package com.gsafety.dawn.community.manage.contract.model;

import java.util.Date;

/**
 * description:
 *
 * @outhor liujian
 * @create 2020-02-12 11:26
 */
public class RoleAssCommunityModel {

    private String id;

    // 冗余code
    private String code;

    // 存储行政区划code集合
    private String administrativeCodes;

    // 社区名称
    private String name;

    // 存储角色信息集合
    private String rolesInformation;

    private Date createTime;

    private Date updateTime;

    private String operateUser;

    // 多租户
    private String multiTenancy;

    // 描述
    private String description;

    public RoleAssCommunityModel() {
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

    public String getAdministrativeCodes() {
        return administrativeCodes;
    }

    public void setAdministrativeCodes(String administrativeCodes) {
        this.administrativeCodes = administrativeCodes;
    }

    public String getRolesInformation() {
        return rolesInformation;
    }

    public void setRolesInformation(String rolesInformation) {
        this.rolesInformation = rolesInformation;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOperateUser() {
        return operateUser;
    }

    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
    }

    public String getMultiTenancy() {
        return multiTenancy;
    }

    public void setMultiTenancy(String multiTenancy) {
        this.multiTenancy = multiTenancy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

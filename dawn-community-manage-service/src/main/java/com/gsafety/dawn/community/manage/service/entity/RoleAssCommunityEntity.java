package com.gsafety.dawn.community.manage.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * description:
 *
 * @outhor liujian
 * @create 2020-02-12 11:08
 */
@Entity
@Table(name = "b_role_ass_community")
public class RoleAssCommunityEntity {

    @Id
    @Column(name = "id",length = 64,nullable = false)
    private String id;

    @Column(name = "code",length = 64,nullable = false)
    private String code;

    @Column(name = "name",length = 64,nullable = false)
    private String name;

    // 存储行政区划code集合
    @Column(name = "administrative_codes",columnDefinition = "text",nullable = false)
    private String administrativeCodes;

    // 存储角色信息集合
    @Column(name = "roles_information",columnDefinition = "text",nullable = false)
    private String rolesInformation;

    @Column(name = "create_time",length = 128)
    private Date createTime;

    @Column(name = "update_time",length = 128)
    private Date updateTime;

    @Column(name = "operate_user",length = 128)
    private String operateUser;

    // 多租户
    @Column(name="multi_tenancy",length = 1024)
    private String multiTenancy;

    // 描述
    @Column(name="description" , columnDefinition = "text")
    private String description;

    public RoleAssCommunityEntity() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

package com.gsafety.dawn.community.manage.contract.model;

import java.util.Date;

/**
 * @create 2020-02-07 22:55
 */
public class ResourceModel {
    private String id;

    private String code;

    private String name;

    private String resCode;

    // 资源行政区划
    private String resAdministrative;

    // 资源类型
    private String resourceTypeEntity;

    private int count;

    // 资源单位
    private String resUnit;

    // 描述
    private String description;

    // 资源规格
    private String resSpecification;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

    // 资源拓展字段
    private String resExpand;

    // 多租户
    private String multiTenancy;

    /**
     * Instantiates a new Resource entity.
     */
    public ResourceModel() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets res code.
     *
     * @return the res code
     */
    public String getResCode() {
        return resCode;
    }

    /**
     * Sets res code.
     *
     * @param resCode the res code
     */
    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    /**
     * Gets res administrative.
     *
     * @return the res administrative
     */
    public String getResAdministrative() {
        return resAdministrative;
    }

    /**
     * Sets res administrative.
     *
     * @param resAdministrative the res administrative
     */
    public void setResAdministrative(String resAdministrative) {
        this.resAdministrative = resAdministrative;
    }

    /**
     * Gets resource type entity.
     *
     * @return the resource type entity
     */
    public String getResourceTypeEntity() {
        return resourceTypeEntity;
    }

    /**
     * Sets resource type entity.
     *
     * @param resourceTypeEntity the resource type entity
     */
    public void setResourceTypeEntity(String resourceTypeEntity) {
        this.resourceTypeEntity = resourceTypeEntity;
    }

    /**
     * Gets count.
     *
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets count.
     *
     * @param count the count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Gets res unit.
     *
     * @return the res unit
     */
    public String getResUnit() {
        return resUnit;
    }

    /**
     * Sets res unit.
     *
     * @param resUnit the res unit
     */
    public void setResUnit(String resUnit) {
        this.resUnit = resUnit;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets res specification.
     *
     * @return the res specification
     */
    public String getResSpecification() {
        return resSpecification;
    }

    /**
     * Sets res specification.
     *
     * @param resSpecification the res specification
     */
    public void setResSpecification(String resSpecification) {
        this.resSpecification = resSpecification;
    }

    /**
     * Gets create time.
     *
     * @return the create time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Sets create time.
     *
     * @param createTime the create time
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * Gets update time.
     *
     * @return the update time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * Sets update time.
     *
     * @param updateTime the update time
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * Gets res expand.
     *
     * @return the res expand
     */
    public String getResExpand() {
        return resExpand;
    }

    /**
     * Sets res expand.
     *
     * @param resExpand the res expand
     */
    public void setResExpand(String resExpand) {
        this.resExpand = resExpand;
    }

    /**
     * Gets multi tenancy.
     *
     * @return the multi tenancy
     */
    public String getMultiTenancy() {
        return multiTenancy;
    }

    /**
     * Sets multi tenancy.
     *
     * @param multiTenancy the multi tenancy
     */
    public void setMultiTenancy(String multiTenancy) {
        this.multiTenancy = multiTenancy;
    }

    @Override
    public String toString() {
        return "ResourceEntity{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", resCode='" + resCode + '\'' +
                ", resAdministrative='" + resAdministrative + '\'' +
                ", resourceTypeEntity=" + resourceTypeEntity +
                ", count=" + count +
                ", resUnit='" + resUnit + '\'' +
                ", description='" + description + '\'' +
                ", resSpecification='" + resSpecification + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", resExpand=" + resExpand +
                ", multiTenancy='" + multiTenancy + '\'' +
                '}';
    }
}

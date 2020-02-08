package com.gsafety.dawn.community.manage.contract.model;

import java.util.Date;

/**
 * @create 2020-02-07 22:54
 */
public class ResourceInOutRecordModel {
    private String id;

    // 资源id
    private String resourceId;

    // 出or进
    private Boolean inOutStatus;

    // 更新时间
    private Date createTime;

    // 数量
    private int count;

    // 操作人
    private String operateUser;

    // 多租户
    private String multiTenancy;

    // 备注
    private String decription;

    /**
     * Instantiates a new Resource in out record entity.
     */
    public ResourceInOutRecordModel() {
        // 空构造
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
     * Gets resource id.
     *
     * @return the resource id
     */
    public String getResourceId() {
        return resourceId;
    }

    /**
     * Sets resource id.
     *
     * @param resourceId the resource id
     */
    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    /**
     * Gets in out status.
     *
     * @return the in out status
     */
    public Boolean getInOutStatus() {
        return inOutStatus;
    }

    /**
     * Sets in out status.
     *
     * @param inOutStatus the in out status
     */
    public void setInOutStatus(Boolean inOutStatus) {
        this.inOutStatus = inOutStatus;
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
     * Gets operate user.
     *
     * @return the operate user
     */
    public String getOperateUser() {
        return operateUser;
    }

    /**
     * Sets operate user.
     *
     * @param operateUser the operate user
     */
    public void setOperateUser(String operateUser) {
        this.operateUser = operateUser;
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

    /**
     * Gets decription.
     *
     * @return the decription
     */
    public String getDecription() {
        return decription;
    }

    /**
     * Sets decription.
     *
     * @param decription the decription
     */
    public void setDecription(String decription) {
        this.decription = decription;
    }

    @Override
    public String toString() {
        return "ResourceInOutRecordModel{" +
                "id='" + id + '\'' +
                ", resourceId='" + resourceId + '\'' +
                ", inOutStatus=" + inOutStatus +
                ", createTime=" + createTime +
                ", count=" + count +
                ", operateUser='" + operateUser + '\'' +
                ", multiTenancy='" + multiTenancy + '\'' +
                ", decription='" + decription + '\'' +
                '}';
    }
}

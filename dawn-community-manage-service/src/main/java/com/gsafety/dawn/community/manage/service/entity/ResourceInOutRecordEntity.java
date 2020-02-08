package com.gsafety.dawn.community.manage.service.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @create 2020-02-07 22:50
 */
@Entity
@Table(name = "b_resource_record")
public class ResourceInOutRecordEntity {

    @Id
    @Column(name = "id",length = 64,nullable = false)
    private String id;

    // 资源id
    @ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name="resource_id",insertable=false,updatable=false)
    private ResourceEntity resourceEntity;

    // 出or进
    @Column(name = "in_out_status" , nullable = false)
    private Boolean inOutStatus;

    // 更新时间
    @Column(name = "create_time",nullable = false)
    private Date createTime;

    // 数量
    @Column(name = "count" , nullable = false)
    private int count;

    // 操作人
    @Column(name = "opearte_user")
    private String operateUser;

    // 多租户
    @Column(name = "multi_tenancy")
    private String multiTenancy;

    // 描述
    @Column(name = "description",length = 1024)
    private String decription;

    /**
     * Instantiates a new Resource in out record entity.
     */
    public ResourceInOutRecordEntity() {
        // 空构造方法
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
     * Gets resource entity.
     *
     * @return the resource entity
     */
    public ResourceEntity getResourceEntity() {
        return resourceEntity;
    }

    /**
     * Sets resource entity.
     *
     * @param resourceEntity the resource entity
     */
    public void setResourceEntity(ResourceEntity resourceEntity) {
        this.resourceEntity = resourceEntity;
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


}

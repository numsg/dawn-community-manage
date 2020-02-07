package com.gsafety.dawn.community.manage.service.entity;

import javax.persistence.*;

/**
 * The type Data Source Data entity.
 */
@Entity
@Table(name = "b_d_source_data")
public class DSourceDataEntity {
    @Id
    @Column(name = "id",length = 64,nullable = false)
    private String id;

    @Column(name = "name",length = 1024, nullable = false)
    private String name;

    @Column(name = "b_d_source_id",length = 64, nullable = false)
    private String dataSourceId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "b_d_source_id", nullable = false,insertable=false,updatable = false)
    private DataSourceEntity dataSourceEntity;

    @Column(name = "pid",length = 64,nullable = false)
    private String pid;

    @Column(name ="image")
    private String image;

    @Column(name = "img_color",length = 10)
    private String imgColor;

    @Column(name = "multi_tenancy")
    private String multiTenancy;

    @Column(name = "sort" ,length = 10)
    private String sort;


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

    public DataSourceEntity getDataSourceEntity() {
        return dataSourceEntity;
    }

    public void setDataSourceEntity(DataSourceEntity dataSourceEntity) {
        this.dataSourceEntity = dataSourceEntity;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImgColor() {
        return imgColor;
    }

    public void setImgColor(String imgColor) {
        this.imgColor = imgColor;
    }

    public String getMultiTenancy() {
        return multiTenancy;
    }

    public void setMultiTenancy(String multiTenancy) {
        this.multiTenancy = multiTenancy;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}

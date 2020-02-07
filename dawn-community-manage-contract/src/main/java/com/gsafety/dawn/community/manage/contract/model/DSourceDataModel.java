package com.gsafety.dawn.community.manage.contract.model;


/**
 * The type D source data model.
 */
public class DSourceDataModel {
    private String id;
    private String name;
    private String dataSourceId;
    private String pid;
    private String image;
    private String imgColor;
    private String multiTenancy;
    private String sort;
    /**
     * Instantiates a new D source data model.
     */
    public DSourceDataModel() {
        //无参构造器
    }

    public DSourceDataModel(String id, String name, String dataSourceId, String pid, String image, String imgColor, String multiTenancy, String sort) {
        this.id = id;
        this.name = name;
        this.dataSourceId = dataSourceId;
        this.pid = pid;
        this.image = image;
        this.imgColor = imgColor;
        this.multiTenancy = multiTenancy;
        this.sort = sort;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
     * Gets data source id.
     *
     * @return the data source id
     */
    public String getDataSourceId() {
        return dataSourceId;
    }

    /**
     * Sets data source id.
     *
     * @param dataSourceId the data source id
     */
    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }

    /**
     * Gets pid.
     *
     * @return the pid
     */
    public String getPid() {
        return pid;
    }

    /**
     * Sets pid.
     *
     * @param pid the pid
     */
    public void setPid(String pid) {
        this.pid = pid;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets img color.
     *
     * @return the img color
     */
    public String getImgColor() {
        return imgColor;
    }

    /**
     * Sets img color.
     *
     * @param imgColor the img color
     */
    public void setImgColor(String imgColor) {
        this.imgColor = imgColor;
    }

}

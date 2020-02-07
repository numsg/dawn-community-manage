package com.gsafety.dawn.community.manage.contract.model;


import java.util.List;

/**
 * The type Data source model.
 */
public class DataSourceModel {
    private String id;
    private String name;
    private int type;
    private String tag;
    private String description;
    private String image;
    private String originalId; // 资源系统中资源类型的id
    private List<DSourceDataModel> dSourceDataModels;

    /**
     * Instantiates a new Data source model.
     */
    public DataSourceModel() {
        //无参构造器
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
     * Gets type.
     *
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * Gets tag.
     *
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * Sets tag.
     *
     * @param tag the tag
     */
    public void setTag(String tag) {
        this.tag = tag;
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

    public List<DSourceDataModel> getdSourceDataModels() {
        return dSourceDataModels;
    }

    public void setdSourceDataModels(List<DSourceDataModel> dSourceDataModels) {
        this.dSourceDataModels = dSourceDataModels;
    }

    public String getOriginalId() {
        return originalId;
    }

    public void setOriginalId(String originalId) {
        this.originalId = originalId;
    }
}

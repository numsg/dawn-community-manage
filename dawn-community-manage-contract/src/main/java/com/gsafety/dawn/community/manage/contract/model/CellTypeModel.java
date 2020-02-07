package com.gsafety.dawn.community.manage.contract.model;

/**
 * The type Cell type model.
 */
public class CellTypeModel {
    private String id;
    private String name;
    private String description;
    private String pid;
    private String image;
    private String imgColor;
    private String sort;

    /**
     * Instantiates a new Cell type model.
     */
    public CellTypeModel() {
        //无参构造
    }

    public CellTypeModel(String id, String name, String description, String pid, String image, String imgColor, String sort) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.pid = pid;
        this.image = image;
        this.imgColor = imgColor;
        this.sort = sort;
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "CellTypeModel{" + "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pid='" + pid + '\'' +
                ", image='" + image + '\'' +
                ", imgColor='" + imgColor + '\'' +
                ", sort='" + sort + '\'' + '}';
    }
}

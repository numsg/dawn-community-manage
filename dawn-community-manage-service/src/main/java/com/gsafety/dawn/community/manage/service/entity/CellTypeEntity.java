package com.gsafety.dawn.community.manage.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The type Cell type entity.
 */
@Entity
@Table(name = "b_cell_type")
public class CellTypeEntity {
    @Id
    @Column(name = "id",length = 64,nullable = false)
    private String id;

    @Column(name = "name",length = 1024,  nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "pid",length = 64,  nullable = false)
    private String pid;

    @Column(name ="image")
    private String image;

    @Column(name = "img_color",length = 10)
    private String imgColor;

    @Column(name = "sort",length = 10)
    private String sort;

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

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPid() {
        return pid;
    }

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
}

package com.gsafety.dawn.community.manage.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The type Data Source entity.
 */
@Entity
@Table(name = "b_data_source")
public class DataSourceEntity {
    @Id
    @Column(name = "id",length = 64,nullable = false)
    private String id;

    @Column(name = "name",length = 1024,  nullable = false)
    private String name;

    @Column(name ="type", length = 2,nullable = false)
    private int type;

    @Column(name ="tag")
    private String tag;

    @Column(name ="description")
    private String description;

    @Column(name ="image")
    private String image;


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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

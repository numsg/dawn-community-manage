package com.gsafety.dawn.community.manage.contract.model.total;

public class ItemModel {
    private String id;
    private String name;
    private String image;
    private String imgColor;
    private Integer count;

    public ItemModel() {
    }

    public ItemModel(String id, String name, String image, String imgColor, Integer count) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.imgColor = imgColor;
        this.count = count;
    }

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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

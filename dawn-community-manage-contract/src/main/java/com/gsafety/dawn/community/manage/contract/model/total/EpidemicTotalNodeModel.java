package com.gsafety.dawn.community.manage.contract.model.total;

/**
 * @create 2020-02-16 21:04
 */
public class EpidemicTotalNodeModel {

    private String id;

    private String typeName;

    private int count;

    private String color;

    public EpidemicTotalNodeModel() {
        // 空构造
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

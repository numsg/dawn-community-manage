package com.gsafety.dawn.community.manage.contract.model.total;

/**
 * @create 2020-02-16 21:04
 */
public class EpidemicTotalNodeModel {

    private String typeName;

    private int count;

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
}

package com.gsafety.dawn.community.manage.contract.model.total;

/**
 * @create 2020-02-19 13:40
 */
public class TypePersonModel {

    private String personId;

    private boolean typeName;

    private String date;

    public TypePersonModel(String personId, boolean typeName , String date) {
        this.personId = personId;
        this.typeName = typeName;
        this.date = date;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public boolean isTypeName() {
        return typeName;
    }

    public void setTypeName(boolean typeName) {
        this.typeName = typeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

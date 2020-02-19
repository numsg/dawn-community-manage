package com.gsafety.dawn.community.manage.contract.model.total;

/**
 * @create 2020-02-19 13:49
 */
public class TypeOtherModel {

    private String personId;

    private String typeName;

    private String date;

    public TypeOtherModel(String personId, String typeName , String date) {
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

package com.gsafety.dawn.community.manage.contract.model;

/**
 * @create 2020-02-16 16:16
 */
public class ModifyMedicalTreatmentModel {

    // 重点人员记录id
    String id;

    // 就医情况id
    String medicalTreatmentId;

    // 操作人
    String operator;

    public ModifyMedicalTreatmentModel() {
        // 空构造
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMedicalTreatmentId() {
        return medicalTreatmentId;
    }

    public void setMedicalTreatmentId(String medicalTreatmentId) {
        this.medicalTreatmentId = medicalTreatmentId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }
}

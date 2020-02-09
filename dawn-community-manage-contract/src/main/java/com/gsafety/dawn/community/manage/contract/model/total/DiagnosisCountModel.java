package com.gsafety.dawn.community.manage.contract.model.total;

import com.gsafety.dawn.community.manage.contract.model.DSourceDataModel;

public class DiagnosisCountModel {
    private DSourceDataModel dSourceDataModel;
    private Integer count;

    public DiagnosisCountModel() {
    }

    public DiagnosisCountModel(DSourceDataModel dSourceDataModel, Integer count) {
        this.dSourceDataModel = dSourceDataModel;
        this.count = count;
    }

    public DSourceDataModel getdSourceDataModel() {
        return dSourceDataModel;
    }

    public void setdSourceDataModel(DSourceDataModel dSourceDataModel) {
        this.dSourceDataModel = dSourceDataModel;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

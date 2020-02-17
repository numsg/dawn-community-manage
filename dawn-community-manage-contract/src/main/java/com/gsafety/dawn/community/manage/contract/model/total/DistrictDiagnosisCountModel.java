package com.gsafety.dawn.community.manage.contract.model.total;

import java.util.List;

/**
 * description:
 *
 * @outhor liujian
 * @create 2020-02-16 17:18
 */
public class DistrictDiagnosisCountModel {

    // 小区id
    String plotId;

    // 小区名称
    String plotName;

    // 分类
    List<DiagnosisCountModel> diagnosisCountModels;

    public DistrictDiagnosisCountModel() {
        // 空构造
    }

    public String getPlotId() {
        return plotId;
    }

    public void setPlotId(String plotId) {
        this.plotId = plotId;
    }

    public String getPlotName() {
        return plotName;
    }

    public void setPlotName(String plotName) {
        this.plotName = plotName;
    }

    public List<DiagnosisCountModel> getDiagnosisCountModels() {
        return diagnosisCountModels;
    }

    public void setDiagnosisCountModels(List<DiagnosisCountModel> diagnosisCountModels) {
        this.diagnosisCountModels = diagnosisCountModels;
    }
}

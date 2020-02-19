package com.gsafety.dawn.community.manage.contract.model.refactor;

import java.util.List;

public class PlotBuildingUnitPagedResult {
   private Integer total;
   private List<PlotBuildingUnitStatistics> pageContent;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<PlotBuildingUnitStatistics> getPageContent() {
        return pageContent;
    }

    public void setPageContent(List<PlotBuildingUnitStatistics> pageContent) {
        this.pageContent = pageContent;
    }
}

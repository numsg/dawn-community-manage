package com.gsafety.dawn.community.manage.contract.model.refactor;

import java.util.List;

public class PlotBuildingUnitPagedResult {
   private Long total;
   private List<PlotBuildingUnitStatistics> pageContent;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<PlotBuildingUnitStatistics> getPageContent() {
        return pageContent;
    }

    public void setPageContent(List<PlotBuildingUnitStatistics> pageContent) {
        this.pageContent = pageContent;
    }
}

package com.gsafety.dawn.community.manage.contract.model.refactor;

import java.util.List;

public class PlotBuildingUnitPagedResult {
   private Integer totalPages;
   private List<PlotBuildingUnitStatistics> pageContent;

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<PlotBuildingUnitStatistics> getPageContent() {
        return pageContent;
    }

    public void setPageContent(List<PlotBuildingUnitStatistics> pageContent) {
        this.pageContent = pageContent;
    }
}

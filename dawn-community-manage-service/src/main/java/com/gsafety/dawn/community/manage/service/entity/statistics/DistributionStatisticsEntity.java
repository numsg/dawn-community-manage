package com.gsafety.dawn.community.manage.service.entity.statistics;

public class DistributionStatisticsEntity {
    private Long count;
    private String item;

    public DistributionStatisticsEntity(Long count, Object item) {
        this.count = count;
        this.item = item.toString();
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}

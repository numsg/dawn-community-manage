package com.gsafety.dawn.community.manage.contract.model.total;

/**
 * 社区疫情分布情况统计结果
 */
public class DistributionStatisticsResult {
    private String id;
    //private String key; //小区、年龄、性别
    private Integer value;// 数量

    public DistributionStatisticsResult() {
    }

    public DistributionStatisticsResult(Integer value) {
        this.value = value;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}

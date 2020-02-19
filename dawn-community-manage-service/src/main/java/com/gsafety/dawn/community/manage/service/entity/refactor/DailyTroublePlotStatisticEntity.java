package com.gsafety.dawn.community.manage.service.entity.refactor;

/**
 * @create 2020-02-19 10:41
 */
public class DailyTroublePlotStatisticEntity {

    String date;

    long count;

    String plot;

    public DailyTroublePlotStatisticEntity(String date, long count, String plot) {
        this.date = date;
        this.count = count;
        this.plot = plot;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }
}

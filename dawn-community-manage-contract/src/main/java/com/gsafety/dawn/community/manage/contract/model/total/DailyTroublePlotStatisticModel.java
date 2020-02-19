package com.gsafety.dawn.community.manage.contract.model.total;

/**
 * description:
 *
 * @outhor liujian
 * @create 2020-02-19 11:27
 */
public class DailyTroublePlotStatisticModel {

    String date;

    long count;

    String plot;

    public DailyTroublePlotStatisticModel(String date, long count, String plot) {
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

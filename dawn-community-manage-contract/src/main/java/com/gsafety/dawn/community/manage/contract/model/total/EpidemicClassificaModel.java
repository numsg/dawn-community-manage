package com.gsafety.dawn.community.manage.contract.model.total;

/**
 * @create 2020-02-19 16:37
 */
public class EpidemicClassificaModel {

    // 日期
    String date;

    // 今日新增总数
    long total ;

    // 今日新增治愈数
    long cure;

    // 今日新增死亡人数
    long death;

    // 累计总数
    long allTotal;

    // 累积治愈
    long allCure;

    // 累积死亡
    long allDeath;

    public EpidemicClassificaModel( String date , long total, long cure, long death) {
        this.date = date;
        this.total = total;
        this.cure = cure;
        this.death = death;
    }

    public EpidemicClassificaModel() {
        // 空构造
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getCure() {
        return cure;
    }

    public void setCure(long cure) {
        this.cure = cure;
    }

    public long getDeath() {
        return death;
    }

    public void setDeath(long death) {
        this.death = death;
    }

    public long getAllTotal() {
        return allTotal;
    }

    public void setAllTotal(long allTotal) {
        this.allTotal = allTotal;
    }

    public long getAllCure() {
        return allCure;
    }

    public void setAllCure(long allCure) {
        this.allCure = allCure;
    }

    public long getAllDeath() {
        return allDeath;
    }

    public void setAllDeath(long allDeath) {
        this.allDeath = allDeath;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

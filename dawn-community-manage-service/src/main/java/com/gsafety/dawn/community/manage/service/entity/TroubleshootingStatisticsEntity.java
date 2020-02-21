//package com.gsafety.dawn.community.manage.service.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import java.util.Date;
//
///**
// * @create 2020-02-20 9:56
// */
//@Entity
//@Table(name = "b_daily_troubleshoot_statistics")
//public class TroubleshootingStatisticsEntity {
//
//    @Id
//    @Column(name = "id", length = 64, nullable = false)
//    private String id;
//
//    // 小区
//    @Column(name = "plot",length = 128)
//    private String plot;
//
//    // 多租户
//    @Column(name = "multi_tenancy")
//    private String multiTenancy;
//
//    // 时间
//    @Column(name = "create_time", length = 128)
//    private Date createTime;
//
//    // 更新时间
//    @Column(name = "update_time", length = 128)
//    private Date updateTime;
//
//    @Column(name = "today_total", length = 128)
//    private long total;
//
//    @Column(name = "today_fever", length = 128)
//    private long fever;
//
//    @Column(name = "today_contact", length = 128)
//    private long contact;
//
//    @Column(name = "today_confirmed", length = 128)
//    private long confirmed;
//
//    @Column(name = "today_suspect", length = 128)
//    private long suspect;
//
//    @Column(name = "today_ct", length = 128)
//    private long ct;
//
//    @Column(name = "all_total", length = 128)
//    private long allTotal;
//
//    @Column(name = "all_fever", length = 128)
//    private long allFever;
//
//    @Column(name = "all_contact", length = 128)
//    private long allContact;
//
//    @Column(name = "all_confirmed", length = 128)
//    private long allConfirmed;
//
//    @Column(name = "all_suspect", length = 128)
//    private long allSuspect;
//
//    @Column(name = "all_ct", length = 128)
//    private long allCt;
//
//    public TroubleshootingStatisticsEntity() {
//        // 空构造
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getPlot() {
//        return plot;
//    }
//
//    public void setPlot(String plot) {
//        this.plot = plot;
//    }
//
//    public String getMultiTenancy() {
//        return multiTenancy;
//    }
//
//    public void setMultiTenancy(String multiTenancy) {
//        this.multiTenancy = multiTenancy;
//    }
//
//    public Date getCreateTime() {
//        return createTime;
//    }
//
//    public void setCreateTime(Date createTime) {
//        this.createTime = createTime;
//    }
//
//    public Date getUpdateTime() {
//        return updateTime;
//    }
//
//    public void setUpdateTime(Date updateTime) {
//        this.updateTime = updateTime;
//    }
//
//    public long getTotal() {
//        return total;
//    }
//
//    public void setTotal(long total) {
//        this.total = total;
//    }
//
//    public long getFever() {
//        return fever;
//    }
//
//    public void setFever(long fever) {
//        this.fever = fever;
//    }
//
//    public long getContact() {
//        return contact;
//    }
//
//    public void setContact(long contact) {
//        this.contact = contact;
//    }
//
//    public long getConfirmed() {
//        return confirmed;
//    }
//
//    public void setConfirmed(long confirmed) {
//        this.confirmed = confirmed;
//    }
//
//    public long getSuspect() {
//        return suspect;
//    }
//
//    public void setSuspect(long suspect) {
//        this.suspect = suspect;
//    }
//
//    public long getCt() {
//        return ct;
//    }
//
//    public void setCt(long ct) {
//        this.ct = ct;
//    }
//
//    public long getAllTotal() {
//        return allTotal;
//    }
//
//    public void setAllTotal(long allTotal) {
//        this.allTotal = allTotal;
//    }
//
//    public long getAllFever() {
//        return allFever;
//    }
//
//    public void setAllFever(long allFever) {
//        this.allFever = allFever;
//    }
//
//    public long getAllContact() {
//        return allContact;
//    }
//
//    public void setAllContact(long allContact) {
//        this.allContact = allContact;
//    }
//
//    public long getAllConfirmed() {
//        return allConfirmed;
//    }
//
//    public void setAllConfirmed(long allConfirmed) {
//        this.allConfirmed = allConfirmed;
//    }
//
//    public long getAllSuspect() {
//        return allSuspect;
//    }
//
//    public void setAllSuspect(long allSuspect) {
//        this.allSuspect = allSuspect;
//    }
//
//    public long getAllCt() {
//        return allCt;
//    }
//
//    public void setAllCt(long allCt) {
//        this.allCt = allCt;
//    }
//}

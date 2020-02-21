package com.gsafety.dawn.community.manage.contract.model.refactor;

import java.util.Date;

/**
 * description:
 *
 * @outhor liujian
 * @create 2020-02-18 17:09
 */
public class DailyTroubleshootingStatisticModel {

    private String date;

    private Date measuringDate;

    private long total;

    private long fever;

    private long contact;

    private long confirmed;

    private long suspect;

    private long ct;

    private long allTotal;

    private long allFever;

    private long allContact;

    private long allConfirmed;

    private long allSuspect;

    private long allCt;




    public DailyTroubleshootingStatisticModel(String date, long total, long fever, long contact, long confirmed, long suspect, long ct) {
        this.date = date;
        this.total = total;
        this.fever = fever;
        this.contact = contact;
        this.confirmed = confirmed;
        this.suspect = suspect;
        this.ct = ct;
    }

    public DailyTroubleshootingStatisticModel() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getFever() {
        return fever;
    }

    public void setFever(long fever) {
        this.fever = fever;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public long getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(long confirmed) {
        this.confirmed = confirmed;
    }

    public long getSuspect() {
        return suspect;
    }

    public void setSuspect(long suspect) {
        this.suspect = suspect;
    }

    public long getCt() {
        return ct;
    }

    public void setCt(long ct) {
        this.ct = ct;
    }

    public long getAllTotal() {
        return allTotal;
    }

    public void setAllTotal(long allTotal) {
        this.allTotal = allTotal;
    }

    public long getAllFever() {
        return allFever;
    }

    public void setAllFever(long allFever) {
        this.allFever = allFever;
    }

    public long getAllContact() {
        return allContact;
    }

    public void setAllContact(long allContact) {
        this.allContact = allContact;
    }

    public long getAllConfirmed() {
        return allConfirmed;
    }

    public void setAllConfirmed(long allConfirmed) {
        this.allConfirmed = allConfirmed;
    }

    public long getAllSuspect() {
        return allSuspect;
    }

    public void setAllSuspect(long allSuspect) {
        this.allSuspect = allSuspect;
    }

    public long getAllCt() {
        return allCt;
    }

    public void setAllCt(long allCt) {
        this.allCt = allCt;
    }

    public Date getMeasuringDate() {
        return measuringDate;
    }

    public void setMeasuringDate(Date measuringDate) {
        this.measuringDate = measuringDate;
    }
}

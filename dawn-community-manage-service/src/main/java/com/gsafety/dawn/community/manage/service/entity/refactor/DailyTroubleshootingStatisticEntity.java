package com.gsafety.dawn.community.manage.service.entity.refactor;

import java.util.Date;

/**
 * description:
 *
 * @outhor liujian
 * @create 2020-02-18 17:09
 */
public class DailyTroubleshootingStatisticEntity {

    private String date;

    private long total;

    private long fever;

    private long contact;

    private long confirmed;

    private long suspect;

    private long ct;

    public DailyTroubleshootingStatisticEntity(String date, long total, long fever, long contact, long confirmed, long suspect, long ct) {
        this.date = date;
        this.total = total;
        this.fever = fever;
        this.contact = contact;
        this.confirmed = confirmed;
        this.suspect = suspect;
        this.ct = ct;
    }

    public DailyTroubleshootingStatisticEntity() {
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
}

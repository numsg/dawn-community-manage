package com.gsafety.dawn.community.manage.contract.model;

import java.util.Date;

/**
 * description:
 *
 * @outhor liujian
 * @create 2020-02-08 22:29
 */
public class RequestModel {

    private int pageNo;

    private int pageSize;

    private Date startDate;

    private Date endDate;

    private String keyWords;

    public RequestModel() {
        // 空构造
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }
}

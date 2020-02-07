package com.gsafety.dawn.community.manage.contract.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * The type Cell model.
 */
public class CellModel {
    private String id;
    private String key;
    private String name;
    private Date editTime;
    private String layout;
    private String data;
    private String rules;
    private String conditions;
    private String widgets;
    private int widgetCount;
    private String extraInfo;
    private Boolean isTemplate;

    /**
     * Instantiates a new Cell model.
     */
    public CellModel() {
        //空构造器
    }


    public CellModel(String id, String key, String name, Date editTime, String layout, String data, String rules, String conditions, String widgets, int widgetCount, String extraInfo, Boolean isTemplate) {
        this.id = id;
        this.key = key;
        this.name = name;
        this.editTime = editTime;
        this.layout = layout;
        this.data = data;
        this.rules = rules;
        this.conditions = conditions;
        this.widgets = widgets;
        this.widgetCount = widgetCount;
        this.extraInfo = extraInfo;
        this.isTemplate = isTemplate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getWidgets() {
        return widgets;
    }

    public void setWidgets(String widgets) {
        this.widgets = widgets;
    }

    public int getWidgetCount() {
        return widgetCount;
    }

    public void setWidgetCount(int widgetCount) {
        this.widgetCount = widgetCount;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public Boolean getIsTemplate() {
        return isTemplate;
    }

    public void setIsTemplate(Boolean template) {
        isTemplate = template;
    }
}

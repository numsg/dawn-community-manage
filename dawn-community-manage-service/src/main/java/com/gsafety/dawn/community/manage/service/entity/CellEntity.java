package com.gsafety.dawn.community.manage.service.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "b_cell")
public class CellEntity {
    @Id
    @Column(name = "id", length = 64, nullable = false)
    private String id;

    @Column(name = "key", length = 64, nullable = false)
    private String key;

    @Column(name = "name", length = 1024, nullable = false)
    private String name;

    @Column(name = "edit_time", nullable = false)
    private Date editTime;

    @Column(name = "extra_info")
    private String extraInfo;

    @Column(name = "layout", nullable = false)
    private String layout;

    @Column(name = "conditions")
    private String conditions;

    @Column(name = "data", nullable = false)
    private String data;

    @Column(name = "rules", nullable = false)
    private String rules;

    @Column(name = "widgets", nullable = false)
    private String widgets;

    @Column(name = "widget_count", length = 10, nullable = false)
    private int widgetCount;

    public CellEntity() {
        //无参构造
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

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
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

}

package com.gsafety.dawn.community.manage.contract.model.total;

import java.util.List;

/**
 *
 */
public class SpecialCountModel {
    private String date;
    private List<ItemModel> itemModels;

    public SpecialCountModel() {
    }

    public SpecialCountModel(String date, List<ItemModel> itemModels) {
        this.date = date;
        this.itemModels = itemModels;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<ItemModel> getItemModels() {
        return itemModels;
    }

    public void setItemModels(List<ItemModel> itemModels) {
        this.itemModels = itemModels;
    }
}

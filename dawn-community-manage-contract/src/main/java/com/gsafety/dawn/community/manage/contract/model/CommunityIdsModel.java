package com.gsafety.dawn.community.manage.contract.model;

import java.util.List;

/**
 * @create 2020-02-08 20:33
 */
public class CommunityIdsModel {

    private List<String> communityIds;

    public CommunityIdsModel() {
        // 空构造
    }

    public List<String> getCommunityIds() {
        return communityIds;
    }

    public void setCommunityIds(List<String> communityIds) {
        this.communityIds = communityIds;
    }
}

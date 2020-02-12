package com.gsafety.dawn.community.manage.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "b_district_code")
public class DistrictEntity {
    @Id
    @Column(name = "id",length = 36,nullable = false)
    private String id;

    @Column(name = "district_code",length = 32,  nullable = false)
    private String districtCode;

    @Column(name = "name",length = 128,  nullable = false)
    private String name;

    @Column(name = "parent_id",length = 32,  nullable = false)
    private String parentId;

    @Column(name = "committee_id",length = 64)
    private String committeeId;

    @Column(name = "committee_name",length = 64)
    private String committeeName;

    @Column(name = "community_id",length = 64)
    private String communityId;

    @Column(name = "community_name",length = 64)
    private String communityName;

    @Column(name = "longitude",length = 32)
    private float longitude;

    @Column(name = "latitude",length = 32)
    private float latitude;

    @Column(name = "searchName",length = 32)
    private String searchName ;

    @Column(name = "location_origin",length = 512)
    private String locationOrigin ;

    @Column(name = "province_code",length = 5)
    private String provinceCode ;

    @Column(name = "district_level")
    private int districtLevel ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCommitteeId() {
        return committeeId;
    }

    public void setCommitteeId(String committeeId) {
        this.committeeId = committeeId;
    }

    public String getCommitteeName() {
        return committeeName;
    }

    public void setCommitteeName(String committeeName) {
        this.committeeName = committeeName;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getLocationOrigin() {
        return locationOrigin;
    }

    public void setLocationOrigin(String locationOrigin) {
        this.locationOrigin = locationOrigin;
    }

    public int getDistrictLevel() {
        return districtLevel;
    }

    public void setDistrictLevel(int districtLevel) {
        this.districtLevel = districtLevel;
    }

    public String getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }
}

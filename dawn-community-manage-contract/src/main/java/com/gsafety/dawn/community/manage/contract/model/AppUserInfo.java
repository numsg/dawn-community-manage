package com.gsafety.dawn.community.manage.contract.model;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

/**
 * Created by Administrator on 2017/3/3.
 */

@ApiModel
public class AppUserInfo {

    @ApiModelProperty(hidden = true)
    private Long userId;

    @Size(min=1, max =31)
    @NotEmpty
    private String username;

    /**
     *
     */
    public AppUserInfo() {
        // Do nothing
    }

    public AppUserInfo(String username) {
        this.username = username;
    }

    public AppUserInfo(long userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }
}

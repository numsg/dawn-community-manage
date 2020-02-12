package com.gsafety.dawn.community.manage.contract.service;

import com.gsafety.dawn.community.manage.contract.model.RoleAssCommunityModel;

import java.util.List;

public interface RoleAssCommunityService {

    // 新增
    List<RoleAssCommunityModel>  addRoleAssCom(List<RoleAssCommunityModel> roleAssCommunityModels);

    // 修改
    List<RoleAssCommunityModel>  updateRoleAssCom(List<RoleAssCommunityModel>  roleAssCommunityModels);

    // 删除
    boolean deleteRoleAssCom(List<String> roleAssCommunityIds);

    // 查询 roleid
    List<RoleAssCommunityModel> queryByroleIds(List<String> roleIds);

    // 查询 code
    List<RoleAssCommunityModel> queryByRoleCodes(List<String> codes);

}

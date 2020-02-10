package com.gsafety.dawn.community.manage.contract.service;

import com.gsafety.dawn.community.manage.contract.model.AccessControlModel;

public interface AccessControlService {
    AccessControlModel addOneAccessControl(AccessControlModel accessControlModel);

    AccessControlModel modifyOneAccessControl(String id, AccessControlModel accessControlModel);

    Boolean deleteOneAccessControl(String id);
}

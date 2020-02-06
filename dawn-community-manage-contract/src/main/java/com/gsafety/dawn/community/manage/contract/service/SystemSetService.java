package com.gsafety.dawn.community.manage.contract.service;

import com.gsafety.dawn.community.manage.contract.model.SystemSetModel;

/**
 * The interface System set service.
 */
public interface SystemSetService {

    /**
     * Update system set int.
     *
     * @param systemSetModel the system set model
     * @return the int
     */
    int updateSystemSet(SystemSetModel systemSetModel);

}

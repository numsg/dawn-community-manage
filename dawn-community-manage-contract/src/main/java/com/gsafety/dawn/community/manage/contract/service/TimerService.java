package com.gsafety.dawn.community.manage.contract.service;

import com.gsafety.dawn.community.manage.contract.model.changde.RequestParamModel;
import com.gsafety.dawn.community.manage.contract.model.changde.TroubleshootRecordModel;

import java.util.List;

public interface TimerService {

   Boolean flushData(String villageId);

   List<TroubleshootRecordModel>  getDataFromPhone(RequestParamModel requestParamModel);
}

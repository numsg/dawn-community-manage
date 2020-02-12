package com.gsafety.dawn.community.manage.contract.service.refactor;

import com.gsafety.dawn.community.manage.contract.model.refactor.ReportingStaffStatistics;
import com.gsafety.dawn.community.manage.contract.model.refactor.TroubleshootRecord;

import java.util.List;

public interface TroubleshootRecordService {

    boolean add(TroubleshootRecord troubleshootRecord);

    boolean update(TroubleshootRecord troubleshootRecord);

    /**
     * 获取多租户下的社区填报人员统计信息
     *
     * @param multiTenancy
     * @return
     */
    List<ReportingStaffStatistics> getReportingStaffStatistics(String multiTenancy);

}

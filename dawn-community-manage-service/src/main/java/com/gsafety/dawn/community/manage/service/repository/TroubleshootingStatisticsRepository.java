package com.gsafety.dawn.community.manage.service.repository;

import com.gsafety.dawn.community.manage.service.entity.TroubleshootingStatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface TroubleshootingStatisticsRepository extends JpaRepository<TroubleshootingStatisticsEntity, String> {

        @Query()
        List<TroubleshootingStatisticsEntity> findAllByPlotAndMultiTenancyAndCreateTime(String plot , String multiTenancy , Date createDate);
}

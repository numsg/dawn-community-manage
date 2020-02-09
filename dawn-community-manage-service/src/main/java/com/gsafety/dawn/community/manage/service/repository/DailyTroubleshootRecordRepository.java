package com.gsafety.dawn.community.manage.service.repository;


import com.gsafety.dawn.community.manage.contract.model.DailyTroubleshootRecordModel;
import com.gsafety.dawn.community.manage.service.entity.DailyTroubleshootRecordEntity;
import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Repository
@Transactional
public interface DailyTroubleshootRecordRepository extends JpaRepository<DailyTroubleshootRecordEntity, String> {

    @Query
    List<DailyTroubleshootRecordEntity>  queryAllByNameAndPhone(String name , String phone);

    // 查询今日登记的
    @Query(nativeQuery = true , value = "select * from b_daily_troubleshoot_record  where create_time between ?1 and ?2 ;")
    List<DailyTroubleshootRecordEntity> todayRecord(Timestamp t1 , Timestamp t2);

    @Query(nativeQuery = true, value = "select count(*) from b_daily_troubleshoot_record  WHERE plot = ?1 ;")
    Integer queryCountByDiagnosisSituation(String diagnosisSituation);

    @Query(nativeQuery = true , value = "select * from b_daily_troubleshoot_record where plot = ?1 ;")
    List<DailyTroubleshootRecordEntity> queryAllByPlot(String plot);

    @Query(nativeQuery = true , value = "select * from b_daily_troubleshoot_record where plot = ?1 and building = ?2 ;")
    List<DailyTroubleshootRecordEntity> queryAllByPlotAndbAndBuilding(String plot , String build);


    @Query(nativeQuery = true , value = "select count(*) from b_daily_troubleshoot_record  where plot = ?3 and building = ?4 and unit_number = ?5 and create_time between ?1 and ?2 ;")
    Integer todayRecordCon(Timestamp t1 , Timestamp t2 , String plot , String build , String unitNumber);

    @Query(nativeQuery = true , value = "SELECT * from b_daily_troubleshoot_record where plot = ?1 and building = ?2 and unit_number = ?3 and create_time between ?4 and ?5 ;")
    List<DailyTroubleshootRecordEntity> queryAllByPlotAndBuildingAndUnitNumber(String plot , String building , String unit_number , Timestamp t1 , Timestamp t2);

}

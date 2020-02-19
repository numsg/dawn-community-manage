package com.gsafety.dawn.community.manage.service.repository.refactor;

import com.gsafety.dawn.community.manage.service.entity.refactor.PlotBuildingUnitStaffEntity;
import com.gsafety.dawn.community.manage.service.entity.refactor.PlotReportingStaffEntity;
import com.gsafety.dawn.community.manage.service.entity.refactor.TroubleshootRecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface TroubleshootRecordRepository extends JpaRepository<TroubleshootRecordEntity, String>, PagingAndSortingRepository<TroubleshootRecordEntity, String> {

    /**
     * 通过填报事件(年月日)查询排查记录信息
     *
     * @param time 填报时间
     * @return
     */
    @Query("select c from TroubleshootRecordEntity c where to_char(c.createTime,'yyyy-MM-dd')  = :createTime")
    TroubleshootRecordEntity findByCreateTime(@Param("createTime") String time);

    @Query("select c from TroubleshootRecordEntity c where c.personBaseId  = :personBaseId")
    TroubleshootRecordEntity findByPersonBaseId(@Param("personBaseId") String personBaseId);

    @Query(value = "select  new com.gsafety.dawn.community.manage.service.entity.refactor.PlotBuildingUnitStaffEntity(count(c),c.building,c.unitNumber,c.createDate,c.plot," +
            "SUM(CASE WHEN isExceedTemp IS TRUE THEN 1 ELSE 0 END)) " +
            "from TroubleshootRecordEntity c where c.multiTenancy  = :multiTenancy " +
            "group by c.building,c.unitNumber,c.createDate,c.plot")
         //List<PlotBuildingUnitStaffEntity> findPlotBuildingUnitStaff(@Param("multiTenancy") String multiTenancy);
    Page<PlotBuildingUnitStaffEntity> findPlotBuildingUnitStaff(@Param("multiTenancy") String multiTenancy, Pageable pageable);

    @Query(nativeQuery = true ,value = "select count(b.count) from (select count(*) from  b_troubleshoot_record where multi_tenancy=?1 group by building,unit_number,create_date,plot) as b")
    Integer getCountByPlotBuildingUnitStaff( String multiTenancy);


    @Query("select new com.gsafety.dawn.community.manage.service.entity.refactor.PlotReportingStaffEntity(count(c),c.plot) " +
            "from TroubleshootRecordEntity c where c.multiTenancy  = :multiTenancy group by c.plot")
    List<PlotReportingStaffEntity> findPlotReportingStaff(@Param("multiTenancy") String multiTenancy);

    @Query
    List<TroubleshootRecordEntity> queryAllByPlot(String plotId);
}

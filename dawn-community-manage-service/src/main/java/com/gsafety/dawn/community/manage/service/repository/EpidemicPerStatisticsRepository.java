package com.gsafety.dawn.community.manage.service.repository;

import com.gsafety.dawn.community.manage.service.entity.EpidemicPersonEntity;
import com.gsafety.dawn.community.manage.service.entity.statistics.DistributionStatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface EpidemicPerStatisticsRepository extends JpaRepository<EpidemicPersonEntity, String> {

    /**
     * 按小区统计
     * @param diagnosisSituation diagnosisSituation
     * @param multiTenancy multiTenancy
     * @param startTime startTime
     * @param endTime endTime
     * @return list
     */
    @Query(value = "select  new com.gsafety.dawn.community.manage.service.entity.statistics.DistributionStatisticsEntity(count(c),c.villageId)" +
            "from EpidemicPersonEntity c where c.diagnosisSituation  = :diagnosisSituation  and c.multiTenancy  = :multiTenancy  and " +
            "c.diseaseTime between :startTime and :endTime group by c.villageId")
    List<DistributionStatisticsEntity> statisticsByPlot(@Param("diagnosisSituation") String diagnosisSituation, @Param("multiTenancy") String multiTenancy,
                                                        @Param("startTime") Date startTime, @Param("endTime") Date endTime);


    //按性别统计
    @Query(value = "select  new com.gsafety.dawn.community.manage.service.entity.statistics.DistributionStatisticsEntity(count(c),c.gender)" +
            "from EpidemicPersonEntity c where c.diagnosisSituation  = :diagnosisSituation  and c.multiTenancy  = :multiTenancy and " +
             "c.diseaseTime between :startTime and :endTime  group by c.gender")
    List<DistributionStatisticsEntity> statisticsByGender(@Param("diagnosisSituation") String diagnosisSituation, @Param("multiTenancy") String multiTenancy,
                                                      @Param("startTime") Date startTime, @Param("endTime") Date endTime);

    // 按年龄统计
       @Query(value = "select  new com.gsafety.dawn.community.manage.service.entity.statistics.DistributionStatisticsEntity(count(c),c.age)" +
            "from EpidemicPersonEntity c where c.diagnosisSituation  = :diagnosisSituation  and c.multiTenancy  = :multiTenancy and " +
            "c.diseaseTime between :startTime and :endTime group by c.age")
    List<DistributionStatisticsEntity> statisticsByAge(@Param("diagnosisSituation") String diagnosisSituation, @Param("multiTenancy") String multiTenancy,
                                                @Param("startTime") Date startTime, @Param("endTime") Date endTime);


}

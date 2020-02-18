package com.gsafety.dawn.community.manage.service.repository.refactor;

import com.gsafety.dawn.community.manage.service.entity.refactor.DailyTroubleshootingStatisticEntity;
import com.gsafety.dawn.community.manage.service.entity.refactor.TroubleshootHistoryRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface TroubleshootHistoryRecordRepository extends JpaRepository<TroubleshootHistoryRecordEntity, String> {

    @Query("select count(1) from TroubleshootHistoryRecordEntity c where c.personBaseId  = :personBaseId")
    Long findCountByPersonBaseId(@Param("personBaseId") String personBaseId);

    @Query("select c.id from TroubleshootHistoryRecordEntity c where c.personBaseId  = :personBaseId order by c.createDate desc")
    List<String> findIdByPersonBaseId(@Param("personBaseId") String personBaseId);

    @Query("select c.id from TroubleshootHistoryRecordEntity c where c.personBaseId  = :personBaseId and c.createDate = :createDate ")
    List<String> findIdByCreateDateAndPersonBaseId(@Param("personBaseId") String personBaseId, @Param("createDate") Date createDate);

    @Query()
    List<TroubleshootHistoryRecordEntity> findAllByPersonBaseIdAndCreateTimeBetween(String personBaseId , Date dateStart , Date dateEnd  );

}

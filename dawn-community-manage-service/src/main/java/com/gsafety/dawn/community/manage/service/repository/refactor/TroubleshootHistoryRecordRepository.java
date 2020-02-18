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

    @Query("select new com.gsafety.dawn.community.manage.service.entity.refactor.DailyTroubleshootingStatisticEntity(to_char(current_date - 3,'yyyy-MM-dd') , count(*) , count(CASE \n" +
            "WHEN h.isExceedTemp = 't' THEN \n" +
            "1 \n" +
            "END) , count(CASE \n" +
            "WHEN h.isContact = 't' THEN \n" +
            "1 \n" +
            "END)  ,count(\n" +
            "CASE \n" +
            "WHEN h.medicalOpinion = 'f56d6e21-5f64-48d0-a55f-5defd9d4c166' THEN \n" +
            "1 \n" +
            "END\n" +
            ") ,count(CASE \n" +
            "WHEN h.medicalOpinion = '903db428-4f4b-4f67-a5ab-3631a77b633d' THEN \n" +
            "1 \n" +
            "END) , count(CASE \n" +
            "WHEN h.medicalOpinion = '5e959c1b-584a-42d5-a28c-78ad5e57c1fb' THEN \n" +
            "1 \n" +
            "END)  ) from TroubleshootHistoryRecordEntity  as h \n" +
            "where  h.createTime>=current_date-3 AND h.createDate <current_date - 2")
    List<DailyTroubleshootingStatisticEntity> statisticHistoryRecord();



}

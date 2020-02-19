package com.gsafety.dawn.community.manage.service.repository;

import com.gsafety.dawn.community.manage.contract.model.total.DailyTroublePlotStatisticModel;
import com.gsafety.dawn.community.manage.contract.model.total.EpidemicClassificaModel;
import com.gsafety.dawn.community.manage.contract.model.total.TypeOtherModel;
import com.gsafety.dawn.community.manage.contract.model.total.TypePersonModel;
import com.gsafety.dawn.community.manage.service.entity.EpidemicPersonEntity;
import com.gsafety.dawn.community.manage.service.entity.refactor.DailyTroublePlotStatisticEntity;
import com.gsafety.dawn.community.manage.service.entity.refactor.DailyTroubleshootingStatisticEntity;
import com.gsafety.dawn.community.manage.service.entity.refactor.TroubleshootHistoryRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface EpidemicPersonRepository extends JpaRepository<EpidemicPersonEntity, String> {
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE b_epidemic_person SET name = ?2, gender=?3, age=?4, village_id=?5,temperature=?6," +
            "diagnosis_situation=?7, medical_condition=?8,special_situation=?9, submit_time=?10,disease_time=?11," +
            "update_time=?12, note=?13,  multi_tenancy=?14, expend_property=?15,mobile_number=?16 WHERE id = ?1 ;")
    Integer updateEpidemicPerson(String id, String name, String gender, Integer age, String villageId, String temperature,
                                 String diagnosisSituation, String medicalCondition, String specialSituation, Date submitTime,
                                 Date diseaseTime, Date updateTime, String note, String multiTenancy, String expendProperty, String mobileNumber);


    @Query(nativeQuery = true, value = "select count(*) from b_epidemic_person  WHERE diagnosis_situation = ?1 and multi_tenancy=?2 ;")
    Integer queryCountByDiagnosisSituationAndMultiTenancy(String diagnosisSituation, String districtCode);


    List<EpidemicPersonEntity> queryAllByDiagnosisSituationAndMultiTenancyOrderByUpdateTimeAsc(String diagnosisId, String communityId);


    @Query(nativeQuery = true, value = "select to_char(update_time,'YYYY-MM-DD') as update_time  from b_epidemic_person GROUP BY to_char(update_time,'YYYY-MM-DD')  ;")
    List<EpidemicPersonEntity> queryDate();

    // 更新医疗就医情况
    @Modifying
    @Query(nativeQuery = true, value = "update b_epidemic_person set medical_condition =?2 , operator =?3 , disease_time =?4 where id = ?1 ;")
    Integer updateMedical(String id, String medicalId, String operator, Date now);

    // 统计-总体-分类诊疗意见
    @Query()
    Integer countAllByMultiTenancyAndConfirmedDiagnosis(String districtCode, String classificaId);

    // 统计-总体-医疗情况
    @Query()
    Integer countAllByMultiTenancyAndMedicalCondition(String districtCode, String medicalId);

    // 统计-小区-分类诊疗意见
    @Query()
    Integer countAllByMultiTenancyAndVillageIdAndConfirmedDiagnosis(String districtCode, String villageId, String classificaId);

    // 统计-小区-医疗情况
    @Query()
    Integer countAllByMultiTenancyAndVillageIdAndMedicalCondition(String districtCode, String villageId, String medicalId);

    @Query()
    List<EpidemicPersonEntity> queryAllByNameAndMobileNumber(String name, String mobileNumber);


    // 统计图1
    @Query("select new com.gsafety.dawn.community.manage.contract.model.total.DailyTroublePlotStatisticModel(to_char(current_date - (:startIndex),'MM月DD号') , count(*) , h.plot)  \n" +
            "from TroubleshootHistoryRecordEntity  as h  \n" +
            "where h.createTime>=current_date-(:startIndex) AND h.createTime <current_date- (:endIndex) AND  h.multiTenancy  = :multiTenancy AND h.plot in (:plotIds) GROUP BY h.plot\n")
    List<DailyTroublePlotStatisticModel> staPlotsMon(@Param("multiTenancy") String multiTenancy, @Param("plotIds") List<String> plotIds, @Param("startIndex") int startIndex, @Param("endIndex") int endIndex);
    // 统计图1
    @Query("select new com.gsafety.dawn.community.manage.contract.model.total.DailyTroublePlotStatisticModel(to_char(current_date - (:startIndex),'MM月DD号') , count(*) , h.plot)  \n" +
            "from TroubleshootHistoryRecordEntity  as h  \n" +
            "where h.createTime>=current_date-(:startIndex) AND h.createTime <current_date- (:endIndex) AND  h.multiTenancy  = :multiTenancy  GROUP BY h.plot\n")
    List<DailyTroublePlotStatisticModel> staPlotsNull(@Param("multiTenancy") String multiTenancy, @Param("startIndex") int startIndex, @Param("endIndex") int endIndex);







    // 七天之前发热、密切接触、确诊、疑似、ct

    // 计算每天的之前的增量
    @Query("select distinct new com.gsafety.dawn.community.manage.contract.model.total.TypePersonModel(h.personBaseId , h.isExceedTemp , to_char(current_date - (:startIndex) ,'MM月DD日')) " +
            " from TroubleshootHistoryRecordEntity  as h where  h.createTime < current_date-(:startIndex) AND h.isExceedTemp = 't' AND  h.multiTenancy  = :multiTenancy")
    List<TypePersonModel> beforeFeversEveryDay(@Param("multiTenancy") String multiTenancy ,  @Param("startIndex") int startIndex);
    // 密切接触
    @Query("select distinct new com.gsafety.dawn.community.manage.contract.model.total.TypePersonModel(h.personBaseId , h.isContact , to_char(current_date - (:startIndex) ,'MM月DD日')) " +
            " from TroubleshootHistoryRecordEntity  as h where  h.createTime < current_date-(:startIndex) AND h.isContact = 't' AND  h.multiTenancy  = :multiTenancy")
    List<TypePersonModel> beforeContactsEveryDay(@Param("multiTenancy") String multiTenancy ,  @Param("startIndex") int startIndex);
    // 确诊、疑似、ct
    @Query("select distinct new com.gsafety.dawn.community.manage.contract.model.total.TypeOtherModel(h.personBaseId , h.medicalOpinion , to_char(current_date - (:startIndex) ,'MM月DD日')) " +
            " from TroubleshootHistoryRecordEntity  as h where  h.createTime < current_date-(:startIndex) AND h.medicalOpinion = :medicalOpinion AND  h.multiTenancy  = :multiTenancy")
    List<TypeOtherModel> beforeOthersEveryDay(@Param("medicalOpinion") String medicalOpinion , @Param("multiTenancy") String multiTenancy ,  @Param("startIndex") int startIndex);






    // 统计图2
    @Query("select new com.gsafety.dawn.community.manage.service.entity.refactor.DailyTroubleshootingStatisticEntity(to_char(current_date - 1,'MM月DD号') , count(*) , count(CASE \n" +
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
            "END) ) from TroubleshootHistoryRecordEntity  as h \n" +
            "where  h.createTime>=current_date-1 AND h.createTime <current_date AND  h.multiTenancy  = :multiTenancy")
    List<DailyTroubleshootingStatisticEntity> statisticHistoryRecordDayMon(@Param("multiTenancy") String multiTenancy);

    @Query("select new com.gsafety.dawn.community.manage.service.entity.refactor.DailyTroubleshootingStatisticEntity(to_char(current_date - 2,'MM月DD号') , count(*) , count(CASE \n" +
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
            "END) ) from TroubleshootHistoryRecordEntity  as h \n" +
            "where  h.createTime>=current_date-2 AND h.createTime <current_date - 1 AND  h.multiTenancy  = :multiTenancy ")
    List<DailyTroubleshootingStatisticEntity> statisticHistoryRecordTue(@Param("multiTenancy") String multiTenancy);

    @Query("select new com.gsafety.dawn.community.manage.service.entity.refactor.DailyTroubleshootingStatisticEntity(to_char(current_date - 3,'MM月DD号') , count(*) , count(CASE \n" +
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
            "END) ) from TroubleshootHistoryRecordEntity  as h \n" +
            "where  h.createTime>=current_date-3 AND h.createTime <current_date - 2 AND  h.multiTenancy  = :multiTenancy")
    List<DailyTroubleshootingStatisticEntity> statisticHistoryRecordDayWed(@Param("multiTenancy") String multiTenancy);

    @Query("select new com.gsafety.dawn.community.manage.service.entity.refactor.DailyTroubleshootingStatisticEntity(to_char(current_date - 4,'MM月DD号') , count(*) , count(CASE \n" +
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
            "END) ) from TroubleshootHistoryRecordEntity  as h \n" +
            "where  h.createTime>=current_date-4 AND h.createTime <current_date - 3 AND  h.multiTenancy  = :multiTenancy")
    List<DailyTroubleshootingStatisticEntity> statisticHistoryRecordThu(@Param("multiTenancy") String multiTenancy);

    @Query("select new com.gsafety.dawn.community.manage.service.entity.refactor.DailyTroubleshootingStatisticEntity(to_char(current_date - 5,'MM月DD号') , count(*) , count(CASE \n" +
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
            "END) ) from TroubleshootHistoryRecordEntity  as h \n" +
            "where  h.createTime>=current_date-5 AND h.createTime <current_date - 4 AND  h.multiTenancy  = :multiTenancy")
    List<DailyTroubleshootingStatisticEntity> statisticHistoryRecordFri(@Param("multiTenancy") String multiTenancy);

    @Query("select new com.gsafety.dawn.community.manage.service.entity.refactor.DailyTroubleshootingStatisticEntity(to_char(current_date - 6,'MM月DD号') , count(*) , count(CASE \n" +
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
            "END) ) from TroubleshootHistoryRecordEntity  as h \n" +
            "where  h.createTime>=current_date-6 AND h.createTime <current_date - 5 AND  h.multiTenancy  = :multiTenancy")
    List<DailyTroubleshootingStatisticEntity> statisticHistoryRecordSat(@Param("multiTenancy") String multiTenancy);

    @Query("select new com.gsafety.dawn.community.manage.service.entity.refactor.DailyTroubleshootingStatisticEntity(to_char(current_date - 7,'MM月DD号') , count(*) , count(CASE \n" +
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
            "END) ) from TroubleshootHistoryRecordEntity  as h \n" +
            "where  h.createTime>=current_date-7 AND h.createTime <current_date - 6 AND  h.multiTenancy  = :multiTenancy")
    List<DailyTroubleshootingStatisticEntity> statisticHistoryRecordSun(@Param("multiTenancy") String multiTenancy);


    // 查询8天以前的综合
    @Query("select new com.gsafety.dawn.community.manage.service.entity.refactor.DailyTroubleshootingStatisticEntity(to_char(current_date - 7,'MM月DD号') , count(*) , count(CASE \n" +
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
            "END) ) from TroubleshootHistoryRecordEntity  as h \n" +
            "where  h.createTime < current_date-7 AND  h.multiTenancy  = :multiTenancy")
    List<DailyTroubleshootingStatisticEntity> statisticHistoryRecordBefore(@Param("multiTenancy") String multiTenancy);




    // 自愈死亡
    @Query("select new com.gsafety.dawn.community.manage.contract.model.total.EpidemicClassificaModel(" +
            "to_char(current_date - (:startIndex) ,'MM月DD号') , count(*)  ,count(CASE \n" +
            "WHEN b.medicalCondition = :cure THEN \n" +
            "1 \n" +
            "END) , count(CASE \n" +
            "WHEN b.medicalCondition = :death THEN \n" +
            "1 \n" +
            "END) )\n" +
            "from EpidemicPersonEntity  as b \n" +
            "where  b.multiTenancy  = :multiTenancy AND b.diseaseTime>=current_date-(:startIndex) AND b.diseaseTime <current_date-(:endIndex)   ")
    List<EpidemicClassificaModel> statisEpidemic(@Param("multiTenancy") String multiTenancy , @Param("startIndex") int startIndex, @Param("endIndex") int endIndex ,
                                                 @Param("cure") String cure , @Param("death") String death);

    @Query("select new com.gsafety.dawn.community.manage.contract.model.total.EpidemicClassificaModel(" +
            "to_char(current_date  ,'MM月DD号') , count(*)  ,count(CASE \n" +
            "WHEN b.medicalCondition = :cure THEN \n" +
            "1 \n" +
            "END) , count(CASE \n" +
            "WHEN b.medicalCondition = :death THEN \n" +
            "1 \n" +
            "END) )\n" +
            "from EpidemicPersonEntity  as b \n" +
            "where  b.multiTenancy  = :multiTenancy AND b.diseaseTime>=current_date AND b.diseaseTime <current_date + 1 ")
    List<EpidemicClassificaModel> statisEpidemicToday(@Param("multiTenancy") String multiTenancy ,
                                                 @Param("cure") String cure , @Param("death") String death);


    // 7天之前所有的治愈和死亡
    @Query("select new com.gsafety.dawn.community.manage.contract.model.total.EpidemicClassificaModel(" +
            "to_char(current_date - 7 ,'MM月DD号') , count(*)  ,count(CASE \n" +
            "WHEN b.medicalCondition = :cure THEN \n" +
            "1 \n" +
            "END) , count(CASE \n" +
            "WHEN b.medicalCondition = :death THEN \n" +
            "1 \n" +
            "END) )\n" +
            "from EpidemicPersonEntity  as b \n" +
            "where  b.multiTenancy  = :multiTenancy AND b.diseaseTime < current_date-7 ")
    List<EpidemicClassificaModel> statisEpidemicBefore(@Param("multiTenancy") String multiTenancy ,@Param("cure") String cure , @Param("death") String death);
}

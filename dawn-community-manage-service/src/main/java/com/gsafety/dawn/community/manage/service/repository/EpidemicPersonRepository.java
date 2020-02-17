package com.gsafety.dawn.community.manage.service.repository;

import com.gsafety.dawn.community.manage.service.entity.EpidemicPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
                                 Date diseaseTime, Date updateTime, String note, String multiTenancy, String expendProperty,String mobileNumber);



    @Query(nativeQuery = true, value = "select count(*) from b_epidemic_person  WHERE diagnosis_situation = ?1 and multi_tenancy=?2 ;")
    Integer queryCountByDiagnosisSituationAndMultiTenancy(String diagnosisSituation,String districtCode);


    List<EpidemicPersonEntity> queryAllByDiagnosisSituationAndMultiTenancyOrderByUpdateTimeAsc(String diagnosisId,String communityId);


    @Query(nativeQuery = true, value = "select to_char(update_time,'YYYY-MM-DD') as update_time  from b_epidemic_person GROUP BY to_char(update_time,'YYYY-MM-DD')  ;")
    List<EpidemicPersonEntity> queryDate();

    // 更新医疗就医情况
    @Modifying
    @Query(nativeQuery = true , value = "update b_epidemic_person set medical_condition =?2 , operator =?3 , disease_time =?4 where id = ?1 ;")
    Integer updateMedical(String id , String medicalId , String operator , Date now);

    // 统计-总体-分类诊疗意见
    @Query()
    Integer countAllByMultiTenancyAndConfirmedDiagnosis(String districtCode , String classificaId );

    // 统计-总体-医疗情况
    @Query()
    Integer countAllByMultiTenancyAndMedicalCondition(String districtCode , String medicalId);

    // 统计-小区-分类诊疗意见
    @Query()
    Integer countAllByMultiTenancyAndVillageIdAndConfirmedDiagnosis(String districtCode , String villageId , String classificaId );

    // 统计-小区-医疗情况
    @Query()
    Integer countAllByMultiTenancyAndVillageIdAndMedicalCondition(String districtCode , String villageId  ,String medicalId);

    // 根据name和phone确定人员是否存在
    @Query()
    List<EpidemicPersonEntity> queryAllByNameAndMobileNumber(String name , String mobile);

}

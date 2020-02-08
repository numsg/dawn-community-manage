package com.gsafety.dawn.community.manage.service.repository;

import com.gsafety.dawn.community.manage.service.entity.EpidemicPersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

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

    @Query(nativeQuery = true, value = "select count(*) from b_epidemic_person  WHERE diagnosis_situation = ?1 ;")
    Integer queryCountByDiagnosisSituation(String diagnosisSituation);
}

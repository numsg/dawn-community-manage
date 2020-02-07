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
    @Query(nativeQuery = true, value = "UPDATE p_epidemic_person SET name = ?2, gender=?3, address=?4, district=?5, medical_condition=?6," +
            " special_situation=?7, submit_time=?8, note=?9, disease_time=?10, multi_tenancy=?11, expend_property=?12 WHERE id = ?1 ;")
    Integer updateEpidemicPerson(String id, String name, String gender, String address, String district, String medicalCondition,
                           String specialSituation, Date submitTime, String note, Date diseaseTime, String multiTenancy, String expendProperty);
}

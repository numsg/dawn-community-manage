package com.gsafety.dawn.community.manage.service.repository;


import com.gsafety.dawn.community.manage.service.entity.SystemSetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface SystemSetRepository extends JpaRepository<SystemSetEntity,String> {

    // id 名称  log  修改人
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE b_system_set SET name = ?2 ,logo=?3,update_person=?4,update_time=?5  WHERE id = ?1 ;")
    Integer updateRuleType(String id, String systemName, String systemLog, String updateUser, Timestamp dateNow);
}

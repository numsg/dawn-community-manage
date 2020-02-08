package com.gsafety.dawn.community.manage.service.repository;

import com.gsafety.dawn.community.manage.service.entity.ResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ResourceRepository extends JpaRepository<ResourceEntity, String> {

    // 修改资源状态
    @Modifying
    @Query(nativeQuery = true,value ="UPDATE b_resource SET is_delete = ?2 WHERE id = ?1 ;")
    Integer updateOneDSourceData(String resourceId, Boolean isDelete);


}

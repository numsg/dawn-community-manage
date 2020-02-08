package com.gsafety.dawn.community.manage.service.repository;

import com.gsafety.dawn.community.manage.service.entity.QrCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QrCodeRepository extends JpaRepository<QrCodeEntity, String> {
    @Modifying
    @Query("delete from QrCodeEntity qc where qc.businessId in (:businessIds)")
    void deleteByIds(@Param("businessIds") List<String> businessIds);

    List<QrCodeEntity> findByBusinessId(String businessId);

    List<QrCodeEntity> findByBusinessIdIn(List<String> businessIds);
}

package com.gsafety.dawn.community.manage.service.repository;


import com.gsafety.dawn.community.manage.service.entity.DailyTroubleshootRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface DailyTroubleshootRecordRepository extends JpaRepository<DailyTroubleshootRecordEntity, String> {

}

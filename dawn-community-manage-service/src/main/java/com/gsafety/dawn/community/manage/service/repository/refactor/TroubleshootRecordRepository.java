package com.gsafety.dawn.community.manage.service.repository.refactor;

import com.gsafety.dawn.community.manage.service.entity.refactor.TroubleshootRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TroubleshootRecordRepository extends JpaRepository<TroubleshootRecordEntity, String> {

    /**
     * 通过填报事件(年月日)查询排查记录信息
     *
     * @param time 填报时间
     * @return
     */
    @Query("select c from TroubleshootRecordEntity c where to_char(c.createTime,'yyyy-MM-dd')  = :createTime")
    TroubleshootRecordEntity findByCreateTime(@Param("createTime") String time);

    @Query("select c from TroubleshootRecordEntity c where c.personBaseId  = :personBaseId")
    TroubleshootRecordEntity findByPersonBaseId(@Param("personBaseId") String personBaseId);

//    List<TroubleshootRecordEntity> find

}

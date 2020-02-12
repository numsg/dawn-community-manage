package com.gsafety.dawn.community.manage.service.repository.refactor;

import com.gsafety.dawn.community.manage.service.entity.refactor.PersonBaseEntity;
import com.gsafety.dawn.community.manage.service.entity.refactor.PlotReportingStaffEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonBaseRepository extends JpaRepository<PersonBaseEntity, String> {

    /**
     * 通过姓名和电话查询人员基本信息
     *
     * @param name  姓名
     * @param phone 电话
     * @return
     */
    @Query("select c from PersonBaseEntity c where c.name  = :name and c.phone = :phone")
    PersonBaseEntity findByNameAndPhone(@Param("name") String name, @Param("phone") String phone);

}

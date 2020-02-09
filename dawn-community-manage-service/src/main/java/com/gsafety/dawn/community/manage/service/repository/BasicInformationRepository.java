package com.gsafety.dawn.community.manage.service.repository;

import com.gsafety.dawn.community.manage.service.entity.BasicInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public interface BasicInformationRepository extends JpaRepository<BasicInformationEntity,String> {

    @Query()
    List<BasicInformationEntity> findAllByNameAndPhone(String name , String phone);

}

package com.gsafety.dawn.community.manage.service.repository;


import com.gsafety.dawn.community.manage.service.entity.RoleAssCommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RoleAssCommunityRepository extends JpaRepository<RoleAssCommunityEntity, String> {

    @Query()
    List<RoleAssCommunityEntity> findAllByRolesInformation(String roleId);


    @Query()
    List<RoleAssCommunityEntity> findAllByRolesInformationAndAdministrativeCodes(String roleId , String code);

    @Query()
    List<RoleAssCommunityEntity> findByRolesInformationIn(List<String> roleIds);

    @Query
    List<RoleAssCommunityEntity> findByAdministrativeCodesIn(List<String> codes);

}

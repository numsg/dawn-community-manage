package com.gsafety.dawn.community.manage.service.repository;

import com.gsafety.dawn.community.manage.service.entity.AppUserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by numsg on 2017/3/3.
 */
@Repository
public interface UserRepository extends CrudRepository<AppUserEntity,Long> {
}

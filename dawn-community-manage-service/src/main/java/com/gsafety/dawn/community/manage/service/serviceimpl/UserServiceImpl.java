package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.manage.contract.model.AppUserInfo;
import com.gsafety.dawn.community.manage.contract.service.UserService;
import com.gsafety.dawn.community.manage.service.datamappers.UserMapper;
import com.gsafety.dawn.community.manage.service.entity.AppUserEntity;
import com.gsafety.dawn.community.manage.service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by numsg on 2017/3/3.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    @Override
    public AppUserInfo save(AppUserInfo appUserInfo) {
        AppUserEntity userEntity = userMapper.modelToEntity(appUserInfo);
        return userMapper.entityToModel( userRepository.save( userEntity));
    }

    @Override
    public List<AppUserInfo> findAll() {

        final List<AppUserEntity> resultList = new ArrayList<>();
        final Iterable<AppUserEntity> all = userRepository.findAll();
        all.forEach(appUser -> resultList.add(appUser));
        return userMapper.entitiestoModels(resultList);
    }

    @Override
    public boolean testTrans() {

        userRepository.save(new AppUserEntity("nm11"));
        userRepository.save(new AppUserEntity("nm21"));
        userRepository.save(new AppUserEntity("namelengthmorethen10"));
        userRepository.save(new AppUserEntity("nm31"));
        userRepository.save(new AppUserEntity("nm41"));
        return true;
    }
}

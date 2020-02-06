package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.manage.contract.model.SystemSetModel;
import com.gsafety.dawn.community.manage.contract.service.SystemSetService;
import com.gsafety.dawn.community.manage.service.datamappers.SystemSetMapper;
import com.gsafety.dawn.community.manage.service.entity.SystemSetEntity;
import com.gsafety.dawn.community.manage.service.repository.SystemSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The type System set service.
 */
@Service
@Transactional
public class SystemSetServiceImpl implements SystemSetService {

    @Autowired
    private SystemSetMapper systemSetMapper;
    @Autowired
    SystemSetRepository systemSetRepository;

    /**
     * Update system set int.
     *
     * @param systemSetModel the system set model
     * @return the int
     */
    @Override
    public int updateSystemSet(SystemSetModel systemSetModel) {
        SystemSetEntity systemSetEntity = systemSetMapper.modelToEntity(systemSetModel);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String dateNow = df.format(calendar.getTime());
        Timestamp ts = Timestamp.valueOf(dateNow);
        return  systemSetRepository.updateRuleType(systemSetEntity.getId(),systemSetEntity.getSystemName(),
                systemSetEntity.getSystemLogo(),systemSetEntity.getUpdateUser() ,ts );
    }
}

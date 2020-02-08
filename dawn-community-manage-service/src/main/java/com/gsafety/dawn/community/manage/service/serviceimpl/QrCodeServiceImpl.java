package com.gsafety.dawn.community.manage.service.serviceimpl;

import com.gsafety.dawn.community.common.exception.BusinessException;
import com.gsafety.dawn.community.common.exception.ErrorCode;
import com.gsafety.dawn.community.common.util.QrCodeUtil;
import com.gsafety.dawn.community.common.util.StringUtil;
import com.gsafety.dawn.community.manage.contract.model.QrCodeModel;
import com.gsafety.dawn.community.manage.contract.service.QrCodeService;
import com.gsafety.dawn.community.manage.service.datamappers.QrCodeMapper;
import com.gsafety.dawn.community.manage.service.entity.QrCodeEntity;
import com.gsafety.dawn.community.manage.service.repository.QrCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QrCodeServiceImpl implements QrCodeService {

    @Autowired
    private QrCodeRepository qrCodeRepository;
    @Autowired
    private QrCodeMapper qrCodeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public QrCodeModel genQrCode(QrCodeModel qrCodeModel, Integer qrCodeSize) {
        if (qrCodeModel == null || StringUtil.isEmpty(qrCodeModel.getBusinessId())
                || StringUtil.isEmpty(qrCodeModel.getContent())) {
            throw new BusinessException(ErrorCode.PARAM_IS_INVALID);
        }
        qrCodeModel.setQrCodeId(StringUtil.genUUID());
        qrCodeModel.setImage(QrCodeUtil.encodeImage(qrCodeModel.getContent(), qrCodeSize));
        qrCodeModel.setCtime(new Date());

        QrCodeEntity qrCodeEntity = qrCodeMapper.modelToEntity(qrCodeModel);
        QrCodeEntity qrCodeEntityCopy = qrCodeRepository.save(qrCodeEntity);
        return qrCodeMapper.entityToModel(qrCodeEntityCopy);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<QrCodeModel> batchGenQrCode(List<QrCodeModel> qrCodeModels, Integer qrCodeSize) {
        if (CollectionUtils.isEmpty(qrCodeModels)) {
            return Collections.emptyList();
        }

        List<String> businessIds = qrCodeModels.stream()
                .map(QrCodeModel::getBusinessId)
                .distinct()
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(businessIds)) {
            throw new BusinessException(ErrorCode.PARAM_NOT_COMPLETE);
        }
        qrCodeRepository.deleteByIds(businessIds);
        Date ctime = new Date();
        List<QrCodeModel> waitSaveQrCodeModels = qrCodeModels.stream()
                .filter(item -> StringUtil.isNotEmpty(item.getBusinessId()) && StringUtil.isNotEmpty(item.getContent()))
                .peek(item -> {
                    item.setQrCodeId(StringUtil.genUUID());
                    item.setImage(QrCodeUtil.encodeImage(item.getContent(), qrCodeSize));
                    item.setCtime(ctime);
                })
                .collect(Collectors.toList());
        List<QrCodeEntity> qrCodeEntities = qrCodeMapper.modelsToEntities(waitSaveQrCodeModels);
        List<QrCodeEntity> qrCodeEntitiesCopy = qrCodeRepository.saveAll(qrCodeEntities);
        return qrCodeMapper.entitiesToModels(qrCodeEntitiesCopy);
    }

    @Override
    public QrCodeModel findByBusinessId(String businessId) {
        return qrCodeRepository.findByBusinessId(businessId)
                .stream()
                .findFirst()
                .map(item -> qrCodeMapper.entityToModel(item))
                .orElse(null);
    }

    @Override
    public List<QrCodeModel> findByBusinessIds(List<String> businessIds) {
        if (CollectionUtils.isEmpty(businessIds)) {
            return Collections.emptyList();
        }
        List<QrCodeEntity> qrCodeEntities = qrCodeRepository.findByBusinessIdIn(businessIds);
        return qrCodeMapper.entitiesToModels(qrCodeEntities);
    }
}

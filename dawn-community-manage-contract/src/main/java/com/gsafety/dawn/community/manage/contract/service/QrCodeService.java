package com.gsafety.dawn.community.manage.contract.service;

import com.gsafety.dawn.community.manage.contract.model.QrCodeModel;

import java.util.List;

public interface QrCodeService {
    QrCodeModel genQrCode(QrCodeModel qrCodeModel, Integer qrCodeSize);

    List<QrCodeModel> batchGenQrCode(List<QrCodeModel> qrCodeModels, Integer qrCodeSize);

    QrCodeModel findByBusinessId(String businessId);

    List<QrCodeModel> findByBusinessIds(List<String> businessIds);
}

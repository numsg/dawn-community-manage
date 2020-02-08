package com.gsafety.dawn.community.manage.webapi.controller;

import com.gsafety.dawn.community.manage.contract.model.QrCodeModel;
import com.gsafety.dawn.community.manage.contract.service.QrCodeService;
import com.gsafety.java.common.exception.HttpError;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "/api/v1", tags = "QrCode Api")
@RestController
@RequestMapping("/api/v1")
public class QrCodeController {

    @Autowired
    private QrCodeService qrCodeService;

    @ApiOperation(value = "生成单个二维码并保存", notes = "genQrCode")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "OK", response = QrCodeModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 400, message = "Param invalid", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @PostMapping("/qr-codes")
    public ResponseEntity<QrCodeModel> genQrCode(@RequestBody QrCodeModel qrCodeModel,
                                                 @RequestParam(value = "size", required = false) Integer qrCodeSize) {
        QrCodeModel result = qrCodeService.genQrCode(qrCodeModel, qrCodeSize);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @ApiOperation(value = "批量生成二维码并保存", notes = "batchGenQrCode")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "OK", response = QrCodeModel.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 400, message = "Param invalid", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @PostMapping("/qr-codes/batch")
    public ResponseEntity<List<QrCodeModel>> batchGenQrCode(@RequestBody List<QrCodeModel> qrCodeModels,
                                                            @RequestParam(value = "size", required = false) Integer qrCodeSize) {
        List<QrCodeModel> result = qrCodeService.batchGenQrCode(qrCodeModels, qrCodeSize);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @ApiOperation(value = "根据业务ID查询二维码详情", notes = "findByBusinessId")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = QrCodeModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 400, message = "Param invalid", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @GetMapping("/qr-codes/{businessId}")
    public ResponseEntity<QrCodeModel> findByBusinessId(@PathVariable String businessId) {
        QrCodeModel result = qrCodeService.findByBusinessId(businessId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "根据业务ID集合查询二维码详情集合", notes = "findByBusinessIds")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = QrCodeModel.class, responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 400, message = "Param invalid", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @PostMapping("/qr-codes/search")
    public ResponseEntity<List<QrCodeModel>> findByBusinessIds(@RequestBody List<String> businessIds) {
        List<QrCodeModel> result = qrCodeService.findByBusinessIds(businessIds);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}

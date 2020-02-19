package com.gsafety.dawn.community.manage.webapi.controller;

import com.gsafety.dawn.community.manage.contract.model.ModifyMedicalTreatmentModel;
import com.gsafety.dawn.community.manage.contract.model.total.DiagnosisCountModel;
import com.gsafety.dawn.community.manage.contract.model.EpidemicPersonModel;
import com.gsafety.dawn.community.manage.contract.model.total.EpidemicTotalStatisticModel;
import com.gsafety.dawn.community.manage.contract.service.EpidemicPersonService;
import com.gsafety.java.common.exception.HttpError;
import com.gsafety.springboot.common.annotation.LimitIPRequestAnnotation;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api", tags = "EpidemicPerson Api")
public class EpidemicPersonController {
    @Autowired
    private EpidemicPersonService epidemicPersonService;

    @PostMapping(value = "/epidemic-person", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "新增一个疫情人员信息", notes = "addOneEpidemicPerson(epidemicPersonModel)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = EpidemicPersonModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<EpidemicPersonModel> addOneEpidemicPerson(@RequestBody @ApiParam(value = "疫情人员信息", required = true) EpidemicPersonModel epidemicPersonModel) {
        epidemicPersonModel.setId(UUID.randomUUID().toString());
        epidemicPersonModel.setUpdateTime(epidemicPersonModel.getSubmitTime());
        epidemicPersonModel.setSubmitTime(new Date());
        epidemicPersonModel.setDiseaseTime(new Date());
        EpidemicPersonModel result = epidemicPersonService.addOneEpidemicPerson(epidemicPersonModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/epidemic-person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "修改一个疫情人员信息", notes = "modifyOneEpidemicPerson(epidemicPersonModel)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = EpidemicPersonModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<EpidemicPersonModel> modifyOneEpidemicPerson(@PathVariable @ApiParam(value = "疫情人员id", required = true) String id,
                                                                       @RequestBody @ApiParam(value = "疫情人员信息", required = true) EpidemicPersonModel epidemicPersonModel) {
        epidemicPersonModel.setUpdateTime(new Date());
        EpidemicPersonModel result = epidemicPersonService.modifyOneEpidemicPerson(id,epidemicPersonModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/epidemic-person/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "删除一个疫情人员信息", notes = "deleteOneEpidemicPerson(id)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Boolean.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<Boolean> deleteOneEpidemicPerson(@PathVariable @ApiParam(value = "疫情人员id", required = true) String id) {
        Boolean result = epidemicPersonService.deleteOneEpidemicPerson(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/epidemic-person/total/all/{districtCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "统计诊断每种情况的数量", notes = "diagnosisCount()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DiagnosisCountModel.class,responseContainer = "List" ),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<DiagnosisCountModel>> diagnosisCount(@PathVariable @ApiParam(value = "社区code") String districtCode) {
        List<DiagnosisCountModel> result = epidemicPersonService.diagnosisCount(districtCode);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    // 更新就医情况信息
    @PutMapping(value = "/epidemic-person/medical-treatment", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "更新就医情况信息", notes = "modifyMedicalTreatment()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Boolean.class,responseContainer = "Boolean" ),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<Boolean> modifyMedicalTreatment(@RequestBody @ApiParam(value = "更新就医情况信息")ModifyMedicalTreatmentModel modifyMedicalTreatmentModel) {
        boolean result = epidemicPersonService.modifyMedicalTreatment(modifyMedicalTreatmentModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 统计-总体-分类诊疗意见
    @GetMapping(value = "/epidemic-person/overall-classification/{districtCode}/{classifiedId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "统计-总体-分类诊疗意见", notes = "overallClassification()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = EpidemicTotalStatisticModel.class,responseContainer = "EpidemicTotalStatisticModel" ),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<EpidemicTotalStatisticModel> overallClassification(@PathVariable @ApiParam(value = "行政区划code") String districtCode ,
                                                                           @PathVariable @ApiParam(value = "分类诊疗意见数据源id") String classifiedId ) {
        EpidemicTotalStatisticModel result = epidemicPersonService.overallClassification(districtCode , classifiedId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 统计-总体-医疗情况
    @GetMapping(value = "/epidemic-person/overall-medical/{districtCode}/{medicalId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "统计-总体-医疗情况", notes = "overallClassification()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = EpidemicTotalStatisticModel.class,responseContainer = "EpidemicTotalStatisticModel" ),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<EpidemicTotalStatisticModel> overallMedical(@PathVariable @ApiParam(value = "行政区划code") String districtCode ,
                                                                    @PathVariable @ApiParam(value = "医疗情况数据源Id") String medicalId ) {
        EpidemicTotalStatisticModel result = epidemicPersonService.overallMedical(districtCode , medicalId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 统计-小区-分类诊疗意见
    @GetMapping(value = "/epidemic-person/plot-classification/{districtCode}/{classifiedId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "统计-小区-分类诊疗意见", notes = "overallClassification()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class,responseContainer = "List" ),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<EpidemicTotalStatisticModel>> ploClassification(@PathVariable @ApiParam(value = "行政区划code") String districtCode ,
                                                                    @PathVariable @ApiParam(value = "分类诊疗意见数据源Id") String classifiedId ) {
        List<EpidemicTotalStatisticModel> result = epidemicPersonService.plotClassification(districtCode , classifiedId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 统计-小区-医疗情况
    @GetMapping(value = "/epidemic-person/plot-medical/{districtCode}/{medicalId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "统计-小区-医疗情况", notes = "overallClassification()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class,responseContainer = "List" ),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<EpidemicTotalStatisticModel>> plotMedical(@PathVariable @ApiParam(value = "行政区划code") String districtCode ,
                                                                    @PathVariable @ApiParam(value = "医疗情况数据源Id") String medicalId ) {
        List<EpidemicTotalStatisticModel> result = epidemicPersonService.plotMedical(districtCode , medicalId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

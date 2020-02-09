package com.gsafety.dawn.community.manage.webapi.controller;

import com.gsafety.dawn.community.manage.contract.model.total.DiagnosisCountModel;
import com.gsafety.dawn.community.manage.contract.model.EpidemicPersonModel;
import com.gsafety.dawn.community.manage.contract.model.total.SpecialCountModel;
import com.gsafety.dawn.community.manage.contract.service.EpidemicPersonService;
import com.gsafety.java.common.exception.HttpError;
import com.gsafety.springboot.common.annotation.LimitIPRequestAnnotation;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping(value = "/epidemic-person/total/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "统计诊断每种的数量", notes = "diagnosisCount()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DiagnosisCountModel.class,responseContainer = "List" ),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<DiagnosisCountModel>> diagnosisCount() {
        List<DiagnosisCountModel> result = epidemicPersonService.diagnosisCount();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/epidemic-person/total/confirmed-suspected/{communityId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "累计确诊/疑似数量统计", notes = "diagnosisCountWithConfirmedAndSuspected()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DiagnosisCountModel.class,responseContainer = "List" ),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<SpecialCountModel>> diagnosisCountWithConfirmedAndSuspected(@PathVariable @ApiParam(value = "社区id") String communityId) {
        List<SpecialCountModel> result = epidemicPersonService.diagnosisCountWithConfirmedAndSuspected(communityId);;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/epidemic-person/total/health-death/{communityId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "累计治愈/死亡数量统计", notes = "diagnosisCountWithHealthAndDeath()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DiagnosisCountModel.class,responseContainer = "List" ),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<SpecialCountModel>> diagnosisCountWithHealthAndDeath(@PathVariable @ApiParam(value = "社区id") String communityId) {
        List<SpecialCountModel> result = epidemicPersonService.diagnosisCountWithHealthAndDeath(communityId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

package com.gsafety.dawn.community.manage.webapi.controller.refactor;

import com.gsafety.dawn.community.manage.contract.model.AccessControlModel;
import com.gsafety.dawn.community.manage.contract.model.refactor.BuildingUnitStatistics;
import com.gsafety.dawn.community.manage.contract.model.refactor.ReportingStaffStatistics;
import com.gsafety.dawn.community.manage.contract.model.refactor.TroubleshootRecord;
import com.gsafety.dawn.community.manage.contract.service.refactor.TroubleshootRecordService;
import com.gsafety.java.common.exception.HttpError;
import com.gsafety.java.common.exception.HttpRestException;
import com.gsafety.springboot.common.annotation.LimitIPRequestAnnotation;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/troubleshoot-record", tags = "TroubleshootRecord Api")
public class TroubleshootRecordController {

    private static final String PARAM_VALIAD_FAILED = "param valiad failed";

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TroubleshootRecordService troubleshootRecordService;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "新增排查记录", notes = "add(troubleshootRecord)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AccessControlModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<Boolean> add(@RequestBody @ApiParam(value = "排查记录信息", required = true) TroubleshootRecord troubleshootRecord
            , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new HttpRestException(HttpStatus.BAD_REQUEST, PARAM_VALIAD_FAILED);
        }
        try {
            boolean result = troubleshootRecordService.add(troubleshootRecord);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("add error", e.getMessage(), e);
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "修改排查记录", notes = "update(troubleshootRecord)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AccessControlModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<Boolean> update(@RequestBody @ApiParam(value = "排查记录信息", required = true) TroubleshootRecord troubleshootRecord
            , BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new HttpRestException(HttpStatus.BAD_REQUEST, PARAM_VALIAD_FAILED);
        }
        try {
            boolean result = troubleshootRecordService.update(troubleshootRecord);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("add error", e.getMessage(), e);
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping(value = "/plot-reporting-staff/{multiTenancy}/multiTenancy", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取社区填报统计", notes = "getPlotReportingStaff(multiTenancy)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Boolean.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<ReportingStaffStatistics>> getPlotReportingStaff(@PathVariable @ApiParam(value = "多租户", required = true) String multiTenancy) {
        try {
            List<ReportingStaffStatistics> result = troubleshootRecordService.getReportingStaffStatistics(multiTenancy);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getPlotReportingStaff error", e.getMessage(), e);
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/building-unit-staff/{plotId}/plotId", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取小区楼栋单元已排查未排查统计", notes = "checkNameIsRepeat(name)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Boolean.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<BuildingUnitStatistics>> getBuildingUnitStatistics(@PathVariable @ApiParam(value = "多租户", required = true) String plotId) {
        try {
            List<BuildingUnitStatistics> result = troubleshootRecordService.getBuildingUnitStatistics(plotId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getBuildingUnitStatistics error", e.getMessage(), e);
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package com.gsafety.dawn.community.manage.webapi.controller;

import com.gsafety.dawn.community.manage.contract.model.CellTypeModel;
import com.gsafety.dawn.community.manage.contract.model.CommunityIdsModel;
import com.gsafety.dawn.community.manage.contract.model.DailyTroubleshootRecordModel;
import com.gsafety.dawn.community.manage.contract.service.DailyTroubleshootRecordService;
import com.gsafety.java.common.exception.HttpError;
import com.gsafety.springboot.common.annotation.LimitIPRequestAnnotation;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @outhor liujian
 * @create 2020-02-08 13:05
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api", tags = "Daily Troubleshoot Record Api")
public class DailyTroubleshootRecordController {

    @Autowired
    DailyTroubleshootRecordService dailyTroubleshootRecordService;

    @PostMapping(value = "/daily-troubleshoot-record", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "新增一条日常排查记录", notes = "addOneroubleshootRecord(dailyTroubleshootRecordModel)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DailyTroubleshootRecordModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<DailyTroubleshootRecordModel> addOneroubleshootRecord(@RequestBody @ApiParam(value = "日常排查记录", required = true) DailyTroubleshootRecordModel dailyTroubleshootRecordModel) {
        DailyTroubleshootRecordModel result = dailyTroubleshootRecordService.addDailyRecord(dailyTroubleshootRecordModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/daily-troubleshoot-record", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "修改一条日常排查记录", notes = "updateOneroubleshootRecord(dailyTroubleshootRecordModel)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DailyTroubleshootRecordModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<DailyTroubleshootRecordModel> updateOneroubleshootRecord(@RequestBody @ApiParam(value = "日常排查记录", required = true) DailyTroubleshootRecordModel dailyTroubleshootRecordModel) {
        DailyTroubleshootRecordModel result=dailyTroubleshootRecordService.updateDailyRecord(dailyTroubleshootRecordModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping(value = "/daily-troubleshoot-record/import")
    @ApiOperation(value = "导入日常排查记录", notes = "importRoubleshootRecord(multipartFile)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<DailyTroubleshootRecordModel>> importRoubleshootRecord(@ApiParam(value = "excel file",required = true) MultipartFile file) {
        List<DailyTroubleshootRecordModel> result = dailyTroubleshootRecordService.importTroubleshootRecord(file);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping(value = "/daily-troubleshoot-record")
    @ApiOperation(value = "查询所有记录按小区分类", notes = "queryRoubleshootRecord()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Map.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<Map<String , List<DailyTroubleshootRecordModel>>> queryRoubleshootRecord() {
        Map<String, List<DailyTroubleshootRecordModel>> listMap = dailyTroubleshootRecordService.queryAll();
        return new ResponseEntity<>(listMap, HttpStatus.OK);
    }


    @PostMapping(value = "/daily-troubleshoot-record/communityIds")
    @ApiOperation(value = "按小区过滤", notes = "queryRoubleshootRecordGroupByPlot()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Map.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<Map<String , List<DailyTroubleshootRecordModel>>>queryRoubleshootRecordGroupByPlot(@RequestBody @ApiParam(value = "小区id集合", required = true) CommunityIdsModel communityIds) {
        Map<String, List<DailyTroubleshootRecordModel>> listMap = dailyTroubleshootRecordService.filterByCommunity(communityIds.getCommunityIds());
        return new ResponseEntity<>(listMap, HttpStatus.OK);
    }

    @GetMapping(value = "/daily-troubleshoot-record/{page}/{pageSize}")
    @ApiOperation(value = "分页", notes = "queryRoubleshootRecord()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<DailyTroubleshootRecordModel>> queryRoubleshootRecordPage(@PathVariable @ApiParam(value = "page", required = true)  int page , @PathVariable @ApiParam(value = "pageSize", required = true) int pageSize) {
        List<DailyTroubleshootRecordModel> result = dailyTroubleshootRecordService.pagQuery(page, pageSize);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/daily-troubleshoot-record/temperature")
    @ApiOperation(value = "体温超标", notes = "queryRoubleshootRecordTemperature")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Map.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<Map<String , List<DailyTroubleshootRecordModel>>> queryRoubleshootRecordTemperature() {
        Map<String, List<DailyTroubleshootRecordModel>> result = dailyTroubleshootRecordService.excessiveBodyTemperature();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping(value = "/daily-troubleshoot-record/{startTime}/{endTime}")
    @ApiOperation(value = "按时间段查询", notes = "queryRoubleshootRecordToday")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Map.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<Map<String , List<DailyTroubleshootRecordModel>>> queryRoubleshootRecordToday(@PathVariable @ApiParam(value = "startTime", required = true)  String startTime , @PathVariable @ApiParam(value = "endTime", required = true) String endTime) {
        Map<String, List<DailyTroubleshootRecordModel>> result = dailyTroubleshootRecordService.registerToda(startTime , endTime);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

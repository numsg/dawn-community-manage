package com.gsafety.dawn.community.manage.webapi.controller;

import com.gsafety.dawn.community.manage.contract.model.refactor.DailyTroubleshootingStatisticModel;
import com.gsafety.dawn.community.manage.contract.service.AnalysisOutbreakService;
import com.gsafety.java.common.exception.HttpError;
import com.gsafety.springboot.common.annotation.LimitIPRequestAnnotation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @create 2020-02-18 17:54
 */

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api", tags = "AnalysisOutbreak Api")
public class AnalysisOutbreakController {

    @Autowired
    AnalysisOutbreakService analysisOutbreakService;

    @GetMapping(value = "/troubleshoot-statistic", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "日常排查历史记录统计", notes = "troubleshootStatistic()")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = List.class), @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class), @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<DailyTroubleshootingStatisticModel>> troubleshootStatistic() {
        List<DailyTroubleshootingStatisticModel> result = analysisOutbreakService.troubleStatistic();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

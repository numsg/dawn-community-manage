package com.gsafety.dawn.community.manage.webapi.controller;

import com.gsafety.dawn.community.manage.contract.model.refactor.TroubleshootRecord;
import com.gsafety.dawn.community.manage.contract.model.total.DistributionStatisticsRequest;
import com.gsafety.dawn.community.manage.contract.model.total.DistributionStatisticsResult;
import com.gsafety.dawn.community.manage.contract.service.StatisticsService;
import com.gsafety.dawn.community.manage.contract.service.TimerService;
import com.gsafety.java.common.exception.HttpError;
import com.gsafety.springboot.common.annotation.LimitIPRequestAnnotation;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api", tags = "Statistics Api")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @PostMapping(value = "/statistics/distribution", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "社区疫情分布情况统计", notes = "distributionStatistics(villageId)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DistributionStatisticsResult.class,responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<DistributionStatisticsResult>> distributionStatistics(@RequestBody @ApiParam(value = "排查记录信息", required = true) DistributionStatisticsRequest paramModel) {
        List<DistributionStatisticsResult> result = statisticsService.distributionStatistics(paramModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

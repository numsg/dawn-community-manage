package com.gsafety.dawn.community.manage.webapi.controller;

import com.gsafety.dawn.community.manage.contract.model.refactor.DailyTroubleshootingStatisticModel;
import com.gsafety.dawn.community.manage.contract.model.total.DailyTroublePlotStatisticModel;
import com.gsafety.dawn.community.manage.contract.model.total.EpidemicClassificaModel;
import com.gsafety.dawn.community.manage.contract.service.AnalysisOutbreakService;
import com.gsafety.dawn.community.manage.contract.service.TroubleshootingStatisticsService;
import com.gsafety.java.common.exception.HttpError;
import com.gsafety.springboot.common.annotation.LimitIPRequestAnnotation;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @create 2020-02-18 17:54
 */

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api", tags = "AnalysisOutbreak Api")
public class AnalysisOutbreakController {

    @Autowired
    AnalysisOutbreakService analysisOutbreakService;

    @Autowired
    TroubleshootingStatisticsService troubleshootingStatisticsService;

    @GetMapping(value = "/troubleshoot-statistic/{districtCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "日常排查历史记录统计", notes = "troubleshootStatistic()")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = List.class), @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class), @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<DailyTroubleshootingStatisticModel>> troubleshootStatistic(@PathVariable @ApiParam(value = "行政区划code", required = true) String districtCode) {
        List<DailyTroubleshootingStatisticModel> result = analysisOutbreakService.troubleStatistic(districtCode);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping(value = "/troubleshoot-statistic/{districtCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "日常排查历史记录按小区统计", notes = "troubleshootPlotStatistic")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = Map.class), @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class), @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<Map<String, List<DailyTroublePlotStatisticModel>>> troubleshootPlotStatistic(@PathVariable @ApiParam(value = "行政区划code", required = true) String districtCode, @RequestBody @ApiParam(value = "小区ids", required = true) List<String> plots) {
        Map<String, List<DailyTroublePlotStatisticModel>> result = analysisOutbreakService.troublePlotStatistic(districtCode, plots);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping(value = "/troubleshoot-overall/{districtCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "日常排查历史记录按统计", notes = "troubleshoot-overall")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<DailyTroubleshootingStatisticModel>> troubleshootOverall(@PathVariable @ApiParam(value = "行政区划code", required = true) String districtCode) {
//        List<DailyTroubleshootingStatisticModel> result = analysisOutbreakService.calcEveryData(districtCode);
        List<DailyTroubleshootingStatisticModel> result = troubleshootingStatisticsService.handleHistoryTroubleShootStatistic(districtCode);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @GetMapping(value = "/epidemic-overall/{districtCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "重点关注人员按死亡和治愈统计", notes = "epidemic-overall")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<EpidemicClassificaModel>> epidemicOverall(@PathVariable @ApiParam(value = "行政区划code", required = true) String districtCode) {
        List<EpidemicClassificaModel> result = analysisOutbreakService.epidemicCureAndDeath(districtCode);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


//    @GetMapping(value = "/troubleshoot-generate", produces = MediaType.APPLICATION_JSON_VALUE)
//    @ApiOperation(value = "生成统计的数据", notes = "troubleshoot-generate")
//    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK", response = List.class),
//            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
//            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
//    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
//    public ResponseEntity<List<EpidemicClassificaModel>> troubleshootGenerate() {
//        troubleshootingStatisticsService.handleHistoryTroubleShootStatistic();
//        return new ResponseEntity<>(null, HttpStatus.OK);
//    }
}

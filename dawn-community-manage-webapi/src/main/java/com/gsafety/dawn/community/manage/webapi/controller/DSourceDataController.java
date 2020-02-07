package com.gsafety.dawn.community.manage.webapi.controller;

import com.gsafety.dawn.community.manage.contract.model.DSourceDataModel;
import com.gsafety.dawn.community.manage.contract.service.DSourceDataService;
import com.gsafety.java.common.exception.HttpError;
import com.gsafety.springboot.common.annotation.LimitIPRequestAnnotation;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * The type data-source data controller.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api", tags = "DSourceData Api")
public class DSourceDataController {
    @Autowired
    private DSourceDataService dSourceDataService;

    /**
     * Add one data-source data entity.
     *
     * @param dSourceDataModel the data-source data model.
     * @return the response entity
     */
    @PostMapping(value = "/data-source-data", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "添加一个数据源数据", notes = "addOneDSource()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DSourceDataModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<DSourceDataModel> addOneDSourceData(@RequestBody @ApiParam(value = "数据源数据", required = true)DSourceDataModel dSourceDataModel) {
        DSourceDataModel  result=dSourceDataService.addOneDSourceData(dSourceDataModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Add one data-source data entity.
     *
     * @param dSourceDataModels the data-source data model.
     * @return the response entity
     */
    @PostMapping(value = "/data-source-data/batch/{isResource}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "批量新增数据源数据", notes = "addOneDSource()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<DSourceDataModel>> addOneDSourceData(@RequestBody @ApiParam(value = "数据源", required = true) List<DSourceDataModel> dSourceDataModels , @PathVariable  @ApiParam(value = "是否使用资源系统数据", required = true) boolean isResource) {
        List<DSourceDataModel>   result = dSourceDataService.addBatchDSourceData(dSourceDataModels , isResource);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * delete  data-source data entity.
     *
     * @param dSourceDataId the data-source data  id.
     * @return the response entity
     */
    @DeleteMapping(value = "/data-source-data/{dSourceDataId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据id删除数据源数据", notes = "deleteOneDSourceData()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DSourceDataModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<Boolean> deleteDSourceData(@PathVariable @ApiParam(value = "数据源数据Id", required = true)String dSourceDataId) {
        Boolean result=dSourceDataService.deleteDSourceData(dSourceDataId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * update one data-source data entity.
     *
     * @param dSourceDataModel the data-source data model
     * @return the response entity
     */
    @PutMapping(value = "/data-source-data", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据id修改一个数据源数据", notes = "deleteOneDSourceData()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DSourceDataModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<DSourceDataModel> updateOneDSourceData(@RequestBody @ApiParam(value = "数据源数据", required = true)DSourceDataModel dSourceDataModel) {
        DSourceDataModel result=dSourceDataService.updateOneDSourceData(dSourceDataModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Gets one d source data by id.
     *
     * @param dSourceDataIds the d source data ids
     * @return the one d source data by id
     */
    @PostMapping(value = "/data-source-data/ids", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据id集合查询多个数据源数据", notes = "getSomeDSourceDataByIds()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DSourceDataModel.class,responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<DSourceDataModel>> getSomeDSourceDataByIds(@RequestBody @ApiParam(value = "数据源数据id集合", required = true)List<String> dSourceDataIds) {
        List<DSourceDataModel> result=dSourceDataService.getSomeDSourceDataByIds(dSourceDataIds);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Gets d source data by ids.
     *
     * @param id the id
     * @return the d source data by ids
     */
    @GetMapping(value = "/data-source-data/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据id查询一个数据源数据", notes = "getOneDSourceDataById()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DSourceDataModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    public ResponseEntity<DSourceDataModel> getOneDSourceDataById(@PathVariable @ApiParam(value = "数据源数据id", required = true)String id) {
       DSourceDataModel result=dSourceDataService.getOneDSourceDataById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Gets one d source data by id.
     *
     * @param dataSourceId the data source id
     * @return the one d source data by id
     */
    @GetMapping(value = "/data-source-data/data-source-id/{dataSourceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据数据源id查询本系统的数据源数据（不包含外部接入系统）,", notes = "getPmsDSourceDataByDataSourceId()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DSourceDataModel.class,responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<DSourceDataModel>> getPmsDSourceDataByDataSourceId(@PathVariable @ApiParam(value = "数据源id", required = true)String dataSourceId) {
        List<DSourceDataModel> result=dSourceDataService.getDSourceDataByDataSourceId(dataSourceId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/data-source-data/data-source-id/external/{originalId}/{isUseRMS}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据数据源id查询 本系统（也包含外部接入系统） 的数据源数据,", notes = "getAllDSourceDataByDataSourceId()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DSourceDataModel.class,responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<List<DSourceDataModel>> getAllDSourceDataByDataSourceId(@PathVariable @ApiParam(value = "数据源originalId", required = true)String originalId ,
                                                                                  @PathVariable @ApiParam(value = "是否启用资源系统的数据源（true-使用，false-不适用）", required = true) Boolean isUseRMS) {
        List<DSourceDataModel> result=dSourceDataService.getAllDSourceDataByDataSourceId(originalId,isUseRMS);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Update d source data sort response entity.
     *
     * @param dSourceDataModels the d source data models
     * @return the response entity
     */
    @PutMapping(value = "/data-source-data/sort", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "修改数据源数据的顺序", notes = "updateDSourceDataSort()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DSourceDataModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity< List<DSourceDataModel> > updateDSourceDataSort(@RequestBody @ApiParam(value = "数据源数据", required = true) List<DSourceDataModel>  dSourceDataModels) {
       List<DSourceDataModel>  result=dSourceDataService.updateDSourceDataSort(dSourceDataModels);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}

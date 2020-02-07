package com.gsafety.dawn.community.manage.controller;

import com.gsafety.dawn.community.common.annotation.LimitIPRequestAnnotation;
import com.gsafety.dawn.community.common.exception.HttpError;
import com.gsafety.dawn.community.manage.contract.model.DataSourceModel;
import com.gsafety.dawn.community.manage.contract.service.DataSourceService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The type data source controller.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api", tags = "DataSource Api")
public class DataSourceController {
    @Autowired
    private DataSourceService dataSourceService;


    /**
     * Add one data source entity.
     *
     * @param dataSourceModel the data source model.
     * @return the response entity
     */
    @PostMapping(value = "/data-sources", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "添加一个数据源", notes = "addOneDataSource(dataSource)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DataSourceModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<DataSourceModel> addOneDataSource(@RequestBody  @ApiParam(value = "数据源", required = true) DataSourceModel dataSourceModel) {
        dataSourceModel.setId(UUID.randomUUID().toString());
        DataSourceModel result=dataSourceService.addOneDataSource(dataSourceModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Batch add data source response entity.
     *
     * @param dataSourceModels the data source models
     * @return the response entity
     */
    @PostMapping(value="/data-sources/batch/{isResource}",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "导入数据源", notes = "addOneDataSource(dataSource)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DataSourceModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    public  ResponseEntity<List<DataSourceModel>> batchAddDataSource(@RequestBody @ApiParam(value = "数据源",required = true) List<DataSourceModel> dataSourceModels , @PathVariable  @ApiParam(value = "是否使用资源系统数据", required = true) boolean isResource){
        if(dataSourceModels.isEmpty()){
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.OK);
        }
        List<DataSourceModel> result = dataSourceService.addBatchDataSource(dataSourceModels , isResource);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


    /**
     * Delete one data source entity
     *
     * @param dataSourceId the data source id.
     * @return the response entity
     */
    @DeleteMapping(value = "/data-sources/{dataSourceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据id删除一个数据源", notes = "deleteOneDataSource(id)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DataSourceModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<Boolean>deleteOneDataSource(@PathVariable  @ApiParam(value = "数据源Id", required = true) String dataSourceId) {
        Boolean result=dataSourceService.deleteOneDataSource(dataSourceId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Update one data source entity
     *
     * @param dataSourceModel the data source model.
     * @return the response entity
     */
    @PutMapping(value = "/data-sources", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "修改一个数据源", notes = "updateOneDataSource(dataSource)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DataSourceModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<DataSourceModel>updateOneDataSource(@RequestBody @ApiParam(value = "数据源", required = true) DataSourceModel dataSourceModel) {
        DataSourceModel result=dataSourceService.updateOneDataSource(dataSourceModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Get all data sources response entity.
     *
     * @return the response entity
     */
    @GetMapping(value = "/data-sources/{isUseRMS}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询所有数据源（包括外部资源系统）", notes = "getAllDataSources()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = DataSourceModel.class,responseContainer = "List"),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity< List<DataSourceModel> >getAllDataSources(@PathVariable @ApiParam(value = "是否启用资源系统的数据源（true-使用，false-不适用）", required = true) Boolean isUseRMS) {
        List<DataSourceModel> result=dataSourceService.getAllDataSources(isUseRMS);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }





}

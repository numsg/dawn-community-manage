package com.gsafety.dawn.community.manage.webapi.controller;

import com.gsafety.dawn.community.manage.contract.model.ResourceModel;
import com.gsafety.dawn.community.manage.contract.service.ResourceService;
import com.gsafety.java.common.exception.HttpError;
import com.gsafety.springboot.common.annotation.LimitIPRequestAnnotation;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @create 2020-02-07 23:08
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api", tags = "Resource Api")
public class ResourceController {
    @Autowired
    private ResourceService resourceService;

    @PostMapping(value = "/resource", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "新增资源", notes = "addOneDSource()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResourceModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<ResourceModel> addOneResource(@RequestBody @ApiParam(value = "资源", required = true) ResourceModel resourceModel) {
        ResourceModel result = resourceService.addResource(resourceModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/resource/{resourceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据id删除资源", notes = "deleteOneResource()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResourceModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<Boolean> deleteResource(@PathVariable @ApiParam(value = "资源Id", required = true)String resourceId) {
        Boolean result = resourceService.deleteResource(resourceId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/resource", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "修改资源", notes = "deleteOneDSourceData()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResourceModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<ResourceModel> updateOneResource(@RequestBody @ApiParam(value = "资源", required = true) ResourceModel resourceModel) {
        ResourceModel result = resourceService.updateResource(resourceModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

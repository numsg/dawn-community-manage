package com.gsafety.dawn.community.manage.webapi.controller;

import com.gsafety.dawn.community.manage.contract.model.ResourceInOutRecordModel;
import com.gsafety.dawn.community.manage.contract.service.ResourceInOutRecordService;
import com.gsafety.java.common.exception.HttpError;
import com.gsafety.springboot.common.annotation.LimitIPRequestAnnotation;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @create 2020-02-07 23:07
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api", tags = "ResourceInOutRecord Api")
public class ResourceInOutRecordController {

    @Autowired
    ResourceInOutRecordService recordService;

    @PostMapping(value = "/resource-record", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "新增资源", notes = "addOneResourceRecord()")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResourceInOutRecordModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 100, timeSecond = 1000)
    public ResponseEntity<ResourceInOutRecordModel> addOneResourceRecord(@RequestBody @ApiParam(value = "资源记录", required = true) ResourceInOutRecordModel resourceInOutRecordModel) {
        ResourceInOutRecordModel result = recordService.addResource(resourceInOutRecordModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}

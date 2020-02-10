package com.gsafety.dawn.community.manage.webapi.controller;

import com.gsafety.dawn.community.manage.contract.model.AccessControlModel;
import com.gsafety.dawn.community.manage.contract.service.AccessControlService;
import com.gsafety.java.common.exception.HttpError;
import com.gsafety.springboot.common.annotation.LimitIPRequestAnnotation;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api", tags = "AccessControl Api")
public class AccessControlController {
    @Autowired
    private AccessControlService accessControlService;

    @PostMapping(value = "/access-control", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "新增一条出入控制信息", notes = "addOneAccessControl(accessControlModel)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AccessControlModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<AccessControlModel> addOneAccessControl(@RequestBody @ApiParam(value = "出入控制信息", required = true) AccessControlModel accessControlModel) {
        accessControlModel.setId(UUID.randomUUID().toString());
        AccessControlModel result = accessControlService.addOneAccessControl(accessControlModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(value = "/access-control/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "修改一条出入控制信息", notes = "modifyOneAccessControl(id,accessControlModel)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = AccessControlModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<AccessControlModel> modifyOneAccessControl(
            @PathVariable @ApiParam(value = "出入控制id", required = true) String id,
            @RequestBody @ApiParam(value = "出入控制信息", required = true) AccessControlModel accessControlModel) {
        AccessControlModel result = accessControlService.modifyOneAccessControl(id, accessControlModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping(value = "/access-control/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "修改一条出入控制信息", notes = "deleteOneAccessControl(id)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Boolean.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<Boolean> deleteOneAccessControl(
            @PathVariable @ApiParam(value = "出入控制id", required = true) String id) {
        Boolean result = accessControlService.deleteOneAccessControl(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

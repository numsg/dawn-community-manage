package com.gsafety.dawn.community.manage.controller;

import com.gsafety.dawn.community.common.annotation.LimitIPRequestAnnotation;
import com.gsafety.dawn.community.common.exception.HttpError;
import com.gsafety.dawn.community.manage.contract.model.SystemSetModel;
import com.gsafety.dawn.community.manage.contract.service.SystemSetService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api", tags = "System Set Api")
public class SystemSetController {
    @Autowired
    SystemSetService systemSetService;

    @PutMapping(value = "/system-set", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "修改系统图标名称", notes = "updateSystemSet(systemSetModel)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = SystemSetModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<Integer> updateSystemSet(@RequestBody @ApiParam(value = "系统设置", required = true) SystemSetModel systemSetModel) {
        int result = systemSetService.updateSystemSet(systemSetModel);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

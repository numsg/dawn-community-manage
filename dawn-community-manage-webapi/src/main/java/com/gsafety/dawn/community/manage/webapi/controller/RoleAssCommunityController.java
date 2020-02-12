package com.gsafety.dawn.community.manage.webapi.controller;

import com.gsafety.dawn.community.manage.contract.model.RoleAssCommunityModel;
import com.gsafety.dawn.community.manage.contract.service.RoleAssCommunityService;
import com.gsafety.java.common.exception.HttpError;
import com.gsafety.springboot.common.annotation.LimitIPRequestAnnotation;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @create 2020-02-12 13:15
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api", tags = "Role Ass Community Api")
public class RoleAssCommunityController {

    @Autowired
    RoleAssCommunityService roleAssCommunityService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "/role-ass-community/add", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "新增角色行政区划关联", notes = "add(troubleshootRecord)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<RoleAssCommunityModel>> add(@RequestBody @ApiParam(value = "新增角色行政区划关联", required = true) List<RoleAssCommunityModel> roleAssCommunityModels) {
        try {
            List<RoleAssCommunityModel>  result = roleAssCommunityService.addRoleAssCom(roleAssCommunityModels);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("add error", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PutMapping(value = "/role-ass-community/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "修改角色行政区划关联", notes = "update(roleAssCommunityModel)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<RoleAssCommunityModel>> update(@RequestBody @ApiParam(value = "修改角色行政区划关联", required = true) List<RoleAssCommunityModel> roleAssCommunityModels) {
        try {
            List<RoleAssCommunityModel> result = roleAssCommunityService.updateRoleAssCom(roleAssCommunityModels);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("update error", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @DeleteMapping(value = "/role-ass-community/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "删除角色行政区划关联", notes = "delete(roleAssCommunityModels)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Boolean.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<Boolean> delete(@RequestBody @ApiParam(value = "删除角色行政区划的id集合", required = true) List<String> roleAssCommunityIds) {
        try {
            boolean result = roleAssCommunityService.deleteRoleAssCom(roleAssCommunityIds);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("update error", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/role-ass-community/query-roles", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据roleIds查询", notes = "delete(roleAssCommunityModels)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<RoleAssCommunityModel>> queryRoleIds(@RequestBody @ApiParam(value = "角色roleids", required = true) List<String> roleids) {
        try {
            List<RoleAssCommunityModel> result = roleAssCommunityService.queryByroleIds(roleids);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("update error", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/role-ass-community/query-codes", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据codes查询", notes = "delete(roleAssCommunityModels)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = List.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @LimitIPRequestAnnotation(limitCounts = 10, timeSecond = 1000)
    public ResponseEntity<List<RoleAssCommunityModel>> queryCodes(@RequestBody @ApiParam(value = "行政区划codes", required = true) List<String> codes) {
        try {
            List<RoleAssCommunityModel> result = roleAssCommunityService.queryByRoleCodes(codes);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("update error", e.getMessage(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}

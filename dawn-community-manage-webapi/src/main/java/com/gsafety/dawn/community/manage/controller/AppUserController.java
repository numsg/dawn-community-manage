package com.gsafety.dawn.community.manage.controller;

import com.gsafety.dawn.community.common.exception.HttpError;
import com.gsafety.dawn.community.common.exception.HttpRestException;
import com.gsafety.dawn.community.manage.contract.model.AppUserInfo;
import com.gsafety.dawn.community.manage.contract.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by numsg on 2017/3/3.
 */
@RestController
@RequestMapping(value = "/api/v1", produces = "application/json")
@Api(value = "/api/v1", description = "AppUser Api")
public class AppUserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/{username}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppUserInfo> create(@PathVariable String username) {
        //返回类型使用ResponseEntity
        return new ResponseEntity<>(userService.save(new AppUserInfo(username)), HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(
            value = "add user",
            notes= "we can only accept the alarm information write with json!",
            response = AppUserController.class
    )
    public ResponseEntity handleUser( @RequestBody @Validated AppUserInfo appUserInfo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new HttpRestException(HttpStatus.BAD_REQUEST, "param valiad failed");
        }
        return new ResponseEntity<>(userService.save(new AppUserInfo(appUserInfo.getUsername())), HttpStatus.OK);
    }

    @ApiOperation(value = "AppUserController controller", notes = "获取所有用户")
    @ApiResponses(value = {
            @ApiResponse(code = 500, message = "Internal Server Error", response = HttpError.class),
            @ApiResponse(code = 406, message = "Not Acceptable", response = HttpError.class)})
    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppUserInfo>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    /**
     * 测试Service事务
     * @return
     */
    @RequestMapping(value = "/user/test-trans", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> testTrans() {
        return new ResponseEntity<>(userService.testTrans(), HttpStatus.OK);
    }
}

package com.gsafety.dawn.community.manage.webapi.controller;

import com.gsafety.odata.service.NumsgODataController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type O data controller.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/OdataService.svc/**")
public class ODataController extends NumsgODataController {
}

package com.gzmpc.portal.admin.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.gzmpc.portal.admin.constant.AdminApiConstants;
import com.gzmpc.portal.web.controller.HovController;

import io.swagger.annotations.Api;

/**
 *
 * Author: rwe
 * Date: Jan 28, 2021
 *
 * Copyright @ 2021 
 * 
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.POST  })
@RequestMapping(AdminApiConstants.API_ADMIN_PREFIX)
@Api(tags = "参照")
public class SystemHovController extends HovController {

}

package org.exframework.portal.web.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.exframework.portal.exception.NotAuthorizedException;
import org.exframework.portal.web.constants.WebApiConstants;
import org.exframework.portal.web.entity.LoginDTO;
import org.exframework.portal.web.service.PortalWebBaseLoginService;
import org.exframework.portal.web.vo.LoginResponse;
import org.exframework.support.rest.entity.ApiResponseData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

//@RestController
//@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET })
//@Api(tags = "登录")
public class PortalWebLoginController {
	
	@Autowired
	PortalWebBaseLoginService portalWebBaseLoginService;
	
	@Autowired
	private HttpServletRequest request;
	
			
	@ApiOperation(value = "获取表单属性")
	@RequestMapping(value = WebApiConstants.API_LOGIN_ASYNC, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponseData<LoginResponse> in( @Valid @ApiParam(value = "登录信息", required = true) @RequestBody LoginDTO dto) throws NotAuthorizedException {
		return portalWebBaseLoginService.login(dto);
	}
	
	@ApiOperation(value = "登出")
	@RequestMapping(value = WebApiConstants.API_LOGIN_ASYNC, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public void out(@Autowired HttpServletResponse response) throws IOException  {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath());
	}
	
}

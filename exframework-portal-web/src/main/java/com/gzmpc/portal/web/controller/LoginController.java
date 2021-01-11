package com.gzmpc.portal.web.controller;


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

import com.gzmpc.portal.exception.NotAuthorizedException;
import com.gzmpc.portal.web.constants.WebApiConstants;
import com.gzmpc.portal.web.entity.LoginDTO;
import com.gzmpc.portal.web.service.LoginService;
import com.gzmpc.portal.web.vo.LoginResponse;
import com.gzmpc.support.rest.entity.ApiResponseData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.GET })
@Api(tags = "登录")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@Autowired
	private HttpServletRequest request;
	
			
	@ApiOperation(value = "获取表单属性")
	@RequestMapping(value = WebApiConstants.API_LOGIN_ASYNC, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ApiResponseData<LoginResponse> in( @Valid @ApiParam(value = "登录信息", required = true) @RequestBody LoginDTO dto) throws NotAuthorizedException {
		return loginService.login(dto);
	}
	
	@ApiOperation(value = "登出")
	@RequestMapping(value = WebApiConstants.API_LOGIN_ASYNC, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void out(@Autowired HttpServletResponse response) throws IOException  {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath());
	}
	
}

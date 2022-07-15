package org.exframework.portal.auth.controller;


import com.usthe.sureness.provider.annotation.WithoutAuth;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.exframework.portal.auth.dto.LoginRequest;
import org.exframework.portal.auth.service.UserService;
import org.exframework.portal.auth.vo.LoginResponse;
import org.exframework.support.rest.entity.ApiResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 控制类
 *
 * @author pro
 */
@RestController
@Api(value = "login", tags = "登录")
@RequestMapping(value = "/login")
@Validated
public class LoginController {

    @Autowired
    UserService userService;

    @WithoutAuth(mapping = "/login/account", method = "post")
    @ApiOperation(value = "登录接口")
    @PostMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseData<LoginResponse> login(@Valid
                                                @ApiParam(required = true) @RequestBody LoginRequest request) {
        return new ApiResponseData<>(userService.login(request.getUserName(), request.getPassword()));
    }

}
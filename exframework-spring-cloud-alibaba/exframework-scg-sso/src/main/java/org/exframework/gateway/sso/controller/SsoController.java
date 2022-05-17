package org.exframework.gateway.sso.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.exframework.gateway.sso.dto.LoginRequest;
import org.exframework.gateway.sso.dto.BaseLoginResponse;
import org.exframework.gateway.sso.dto.SmsLoginRequest;
import org.exframework.gateway.sso.service.ISsoLoginService;
import org.exframework.spring.boot.captcha.annotation.CaptchaRequired;
import org.exframework.support.rest.entity.ApiResponseData;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 认证
 *
 * @author rwe
 * @version 1.0
 * @date 2021/11/10
 */
public abstract class SsoController<T, R extends BaseLoginResponse> {

    /**
     * 获取实现类
     *
     * @return
     */
    public abstract ISsoLoginService<T, R> getSsoService();


    @CaptchaRequired
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public ApiResponseData<R> login(@ApiParam(value = "登录信息", required = true) @RequestBody @Validated LoginRequest request) {
        return new ApiResponseData(getSsoService().login(request));
    }

    @CaptchaRequired
    @ApiOperation(value = "短信登录")
    @PostMapping("/smsLogin")
    public ApiResponseData<R> smsLogin(@ApiParam(value = "短信登录信息", required = true) @RequestBody @Validated SmsLoginRequest request) {
        return new ApiResponseData(getSsoService().smsLogin(request));
    }

    @ApiOperation(value = "注销")
    @GetMapping(value = "/logout")
    public ApiResponseData<String> logout() {
        getSsoService().logout();
        return new ApiResponseData<>(null);
    }

    @ApiOperation(value = "当前登录信息")
    @GetMapping(value = "/currentUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponseData<T> currentUser() {
        return new ApiResponseData(getSsoService().currentUserDetail());
    }
}

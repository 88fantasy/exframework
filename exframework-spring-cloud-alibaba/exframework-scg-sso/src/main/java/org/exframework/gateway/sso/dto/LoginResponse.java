package org.exframework.gateway.sso.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录响应
 *
 * @author rwe
 * @date 2021/11/10 23:33
 **/
@ApiModel("登录响应")
public class LoginResponse {

    /** token **/
    @ApiModelProperty("token")
    private String token;

    /**是否第一次登录（0-否，1-是） **/
    @ApiModelProperty("是否第一次登录")
    private Integer isFirstLogin;

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public Integer getIsFirstLogin() {
        return isFirstLogin;
    }

    public LoginResponse setIsFirstLogin(Integer isFirstLogin) {
        this.isFirstLogin = isFirstLogin;
        return this;
    }
}

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
public class BaseLoginResponse {

    /** token **/
    @ApiModelProperty("token")
    private String token;

    @ApiModelProperty("是否第一次登录")
    private Boolean firstLogin;

    public String getToken() {
        return token;
    }

    public BaseLoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    public Boolean getFirstLogin() {
        return firstLogin;
    }

    public BaseLoginResponse setFirstLogin(Boolean firstLogin) {
        this.firstLogin = firstLogin;
        return this;
    }
}

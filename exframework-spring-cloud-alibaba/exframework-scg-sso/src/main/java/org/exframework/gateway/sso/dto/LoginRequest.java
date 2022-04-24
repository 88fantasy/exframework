package org.exframework.gateway.sso.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;

/**
 * 登录请求
 *
 * @author rwe
 * @date 2021/11/10 23:38
 **/
@ApiModel("登录请求")
public class LoginRequest {

    @ApiModelProperty(value = "账号/手机号/邮箱", required = true)
    @NotEmpty
    private String username;

    @ApiModelProperty(value = "密码", required = true)
    @NotEmpty
    private String password;

    @ApiModelProperty(value = "登录端口")
    @NotEmpty
    private String device;

    @ApiModelProperty(value = "图片验证码标记", required = true)
    @NotEmpty
    private String sign;

    @ApiModelProperty(value = "验证码", required = true)
    @NotEmpty
    private String captcha;

    public String getUsername() {
        return username;
    }

    public LoginRequest setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginRequest setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getDevice() {
        return device;
    }

    public LoginRequest setDevice(String device) {
        this.device = device;
        return this;
    }

    public String getSign() {
        return sign;
    }

    public LoginRequest setSign(String sign) {
        this.sign = sign;
        return this;
    }

    public String getCaptcha() {
        return captcha;
    }

    public LoginRequest setCaptcha(String captcha) {
        this.captcha = captcha;
        return this;
    }
}

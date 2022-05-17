package org.exframework.gateway.sso.dto;

import cn.hutool.core.lang.RegexPool;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 短信登录请求
 *
 * @author rwe
 * @date 2021/11/11 10:51
 **/
public class SmsLoginRequest {

    @ApiModelProperty(value = "手机号", required = true)
    @NotBlank
    @Pattern(regexp = RegexPool.MOBILE, message = "手机号格式有误")
    private String phone;

    @ApiModelProperty(value = "短信验证码", required = true)
    @NotBlank
    private String sms;

    @ApiModelProperty(value = "登录端口")
    @NotEmpty
    private String device;

    public String getPhone() {
        return phone;
    }

    public SmsLoginRequest setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getSms() {
        return sms;
    }

    public SmsLoginRequest setSms(String sms) {
        this.sms = sms;
        return this;
    }

    public String getDevice() {
        return device;
    }

    public SmsLoginRequest setDevice(String device) {
        this.device = device;
        return this;
    }
}

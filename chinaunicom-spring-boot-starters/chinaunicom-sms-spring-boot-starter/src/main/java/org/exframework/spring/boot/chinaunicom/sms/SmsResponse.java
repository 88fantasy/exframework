package org.exframework.spring.boot.chinaunicom.sms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 短信服务返回
 *
 * @author rwe
 * @date 2021/7/19 11:14
 **/
@ApiModel("短信服务返回")
public class SmsResponse {

    /**
     * 信息状态
     * '000000'正常
     */
    @ApiModelProperty(value = "信息状态,'000000'正常")
    private String statusCode;

    /**
     * 错误信息
     */
    @ApiModelProperty(value = "错误信息")
    private String statusMsg;

    /**
     * 信息标识
     */
    @ApiModelProperty(value = "信息标识")
    private TemplateSms templateSms;

    public String getStatusCode() {
        return statusCode;
    }

    public SmsResponse setStatusCode(String statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public SmsResponse setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
        return this;
    }

    public TemplateSms getTemplateSms() {
        return templateSms;
    }

    public SmsResponse setTemplateSms(TemplateSms templateSms) {
        this.templateSms = templateSms;
        return this;
    }
}

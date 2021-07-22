package org.exframework.spring.boot.autoconfigure.chinaunicom.sms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 模板信息
 *
 * @author rwe
 * @date 2021/7/19 11:16
 **/
@ApiModel("模板信息")
public class TemplateSms {

    /**
     * 每条消息的唯一识别
     */
    @ApiModelProperty(value = "每条消息的唯一识别")
    private String smsMessageSid;

    /**
     * 发送日期
     */
    @ApiModelProperty(value = "发送日期")
    private String dateCreated;

    public String getSmsMessageSid() {
        return smsMessageSid;
    }

    public TemplateSms setSmsMessageSid(String smsMessageSid) {
        this.smsMessageSid = smsMessageSid;
        return this;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public TemplateSms setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }
}

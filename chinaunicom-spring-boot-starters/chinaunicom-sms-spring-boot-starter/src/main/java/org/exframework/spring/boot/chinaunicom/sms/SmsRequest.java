package org.exframework.spring.boot.chinaunicom.sms;

import java.util.List;

/**
 * 短信请求
 *
 * @author rwe
 * @date 2021/7/19 10:53
 **/
public class SmsRequest {

    /**
     * appId
     */
    private String appId;

    /**
     * 发送给谁
     */
    private String to;

    /**
     * 填充字段
     */
    private List<String> datas;

    /**
     * 短信模板id
     */
    private String templateId;

    public String getAppId() {
        return appId;
    }

    public SmsRequest setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public String getTo() {
        return to;
    }

    public SmsRequest setTo(String to) {
        this.to = to;
        return this;
    }

    public List<String> getDatas() {
        return datas;
    }

    public SmsRequest setDatas(List<String> datas) {
        this.datas = datas;
        return this;
    }

    public String getTemplateId() {
        return templateId;
    }

    public SmsRequest setTemplateId(String templateId) {
        this.templateId = templateId;
        return this;
    }
}

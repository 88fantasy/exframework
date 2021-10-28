package org.exframework.spring.boot.chinaunicom.sms;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 短息配置类
 *
 * @author rwe
 * @date 2021/7/19 13:23
 **/
@Component
@ConfigurationProperties(prefix = "sms")
public class SmsProperties {

    /**
     * 请求地址
     */
    private String url;

    /**
     * 接口
     */
    private String api;

    /**
     * 帐号信息
     */
    private String accountSid;

    /**
     * 密钥
     */
    private String authToken;

    /**
     * 应用 Id
     */
    private String appId;

    /**
     * 代理设置
     */
    private Proxy proxy;

    public String getUrl() {
        return url;
    }

    public SmsProperties setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getApi() {
        return api;
    }

    public SmsProperties setApi(String api) {
        this.api = api;
        return this;
    }

    public String getAccountSid() {
        return accountSid;
    }

    public SmsProperties setAccountSid(String accountSid) {
        this.accountSid = accountSid;
        return this;
    }

    public String getAuthToken() {
        return authToken;
    }

    public SmsProperties setAuthToken(String authToken) {
        this.authToken = authToken;
        return this;
    }

    public String getAppId() {
        return appId;
    }

    public SmsProperties setAppId(String appId) {
        this.appId = appId;
        return this;
    }

    public Proxy getProxy() {
        return proxy;
    }

    public SmsProperties setProxy(Proxy proxy) {
        this.proxy = proxy;
        return this;
    }
}

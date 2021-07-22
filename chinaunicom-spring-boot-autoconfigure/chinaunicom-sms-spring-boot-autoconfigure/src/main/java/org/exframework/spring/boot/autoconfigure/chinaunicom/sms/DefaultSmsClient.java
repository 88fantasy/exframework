package org.exframework.spring.boot.autoconfigure.chinaunicom.sms;

import com.dtflys.forest.config.ForestConfiguration;
import org.exframework.support.common.build.IBuilder;
import org.exframework.support.common.util.StrUtils;

/**
 * 短信客户端
 *
 * @author rwe
 * @since Jan 1, 2021
 * <p>
 * Copyright @ 2021
 */

public class DefaultSmsClient implements SmsClient {

    /**
     * 短信配置
     */
    private SmsProperties smsProperties;

    private SmsHttpClient smsHttpClient;

    @Override
    public SmsResponse send(SmsRequest request) {
        if (!StrUtils.hasText(request.getAppId()) && StrUtils.hasText(smsProperties.getAppId())) {
            request.setAppId(smsProperties.getAppId());
        }
        return smsHttpClient.send(smsProperties.getUrl(), smsProperties.getAccountSid(), smsProperties.getApi(), smsProperties.getAuthToken(), request);
    }

    public DefaultSmsClient(SmsProperties smsProperties, SmsHttpClient smsHttpClient) {
        this.smsProperties = smsProperties;
        this.smsHttpClient = smsHttpClient;
    }

    public static class Builder implements IBuilder<DefaultSmsClient> {

        SmsProperties smsProperties;

        SmsHttpClient smsHttpClient;

        public Builder() {
            this.smsProperties = new SmsProperties();
        }

        public Builder properties(SmsProperties smsProperties) {
            this.smsProperties = smsProperties;
            return this;
        }

        public Builder client(SmsHttpClient smsHttpClient) {
            if (smsHttpClient == null) {
                this.smsHttpClient = ForestConfiguration.configuration().createInstance(SmsHttpClient.class);
            } else {
                this.smsHttpClient = smsHttpClient;
            }
            return this;
        }

        public Builder url(String url) {
            this.smsProperties.setUrl(url);
            return this;
        }

        public Builder api(String api) {
            this.smsProperties.setApi(api);
            return this;
        }

        public Builder sid(String accountSid) {
            this.smsProperties.setAccountSid(accountSid);
            return this;
        }

        public Builder token(String authToken) {
            this.smsProperties.setAuthToken(authToken);
            return this;
        }

        public Builder appId(String appId) {
            this.smsProperties.setAppId(appId);
            return this;
        }


        @Override
        public DefaultSmsClient build() {
            return new DefaultSmsClient(this.smsProperties, this.smsHttpClient);
        }
    }

}

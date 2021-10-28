package org.exframework.spring.boot.chinaunicom.sms;

import com.dtflys.forest.annotation.Body;
import com.dtflys.forest.annotation.Post;

/**
 * 短信接口
 *
 * @author rwe
 * @date 2021/7/19 10:18
 **/
public interface SmsHttpClient {

    String URL_KEY = "url";
    String SID_KEY = "sid";
    String API_KEY = "api";
    String TOKEN_KEY = "token";


    /**
     * 发送短信
     *
     * @param url     服务地址
     * @param sid     帐号
     * @param api     接口
     * @param token   密钥
     * @param request 短信内容
     * @return 短信返回
     */
    @Post(url = "${url}/${sid}/${api}", contentType = "application/json", dataType = "json", headers = {"Accept: application/json"}, interceptor = {SmsHeaderInterceptor.class})
    SmsResponse send(@Body(URL_KEY) String url, @Body(SID_KEY) String sid, @Body(API_KEY) String api, @Body(TOKEN_KEY) String token, @Body SmsRequest request);
}

package org.exframework.spring.boot.autoconfigure.chinaunicom.sms;


/**
 * 短信客户端
 *
 * @author rwe
 * @date 2021/7/22 10:39
 **/
public interface SmsClient {

    /**
     * 发送短信
     * @param request 短信内容
     * @return 返回信息
     */
    SmsResponse send(SmsRequest request);
}

/*
 *Copyright © 2018 anji-plus
 *安吉加加信息技术有限公司
 *http://www.anji-plus.com
 *All rights reserved.
 */
package org.exframework.spring.boot.captcha.controller;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.InetAddress;


public abstract class CaptchaController {

    private static final String UNKNOWN = "unknown";

    private static final int IP_MAX_LENGTH = 15;

    @Autowired
    private CaptchaService captchaService;

    public abstract ServerHttpRequest getRequest();


    @PostMapping("/get")
    public ResponseModel get(@RequestBody CaptchaVO data) {
        data.setBrowserInfo(getIpAddress(getRequest()));
        return captchaService.get(data);
    }

    @PostMapping("/check")
    public ResponseModel check(@RequestBody CaptchaVO data) {
        data.setBrowserInfo(getIpAddress(getRequest()));
        return captchaService.check(data);
    }


    public final String getIpAddress(ServerHttpRequest request) {
        //X-Forwarded-For：Squid 服务代理
        String ip = request.getHeaders().getFirst("X-Forwarded-For");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                //Proxy-Client-IP：apache 服务代理
                ip = request.getHeaders().getFirst("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                //WL-Proxy-Client-IP：weblogic 服务代理
                ip = request.getHeaders().getFirst("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                //HTTP_CLIENT_IP：第三方中转代理服务器
                ip = request.getHeaders().getFirst("HTTP_CLIENT_IP");
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                //X-Real-IP：nginx服务代理
                ip = request.getHeaders().getFirst("X-Real-IP");
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeaders().getFirst("HTTP_X_FORWARDED_FOR");
            }
            if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
                InetAddress address = request.getRemoteAddress().getAddress();
                ip = address.getHostAddress();
            }
        }
        //225.225.225.225
        if (ip.length() > IP_MAX_LENGTH) {
            String[] ips = ip.split(",");
            for (int i = 0; i < ips.length; i++) {
                if (!(UNKNOWN.equalsIgnoreCase(ips[i]))) {
                    return ips[i];
                }
            }
        }
        return ip;
    }
}

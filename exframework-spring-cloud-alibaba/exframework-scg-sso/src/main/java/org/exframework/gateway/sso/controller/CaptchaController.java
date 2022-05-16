/*
 *Copyright © 2018 anji-plus
 *安吉加加信息技术有限公司
 *http://www.anji-plus.com
 *All rights reserved.
 */
package org.exframework.gateway.sso.controller;

import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import io.swagger.annotations.Api;
import org.exframework.gateway.utils.NetworkIpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;


@Api(tags = "验证码")
@RestController
@RequestMapping("/captcha")
public class CaptchaController extends org.exframework.spring.boot.captcha.controller.CaptchaController {

    @Override
    public ServerHttpRequest getRequest() {
        return SaReactorSyncHolder.getContext().getRequest();
    }
}

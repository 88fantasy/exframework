package org.exframework.gateway.sso.filter;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.exframework.gateway.utils.GatewayUtils;
import org.exframework.support.common.util.StrUtils;
import org.exframework.support.rest.entity.ApiResponseData;
import org.exframework.support.rest.enums.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;

/**
 * 校验验证码
 *
 * @author rwe
 * @date 2021/9/30 18:50
 **/
public class CaptchaFilter implements WebFilter, Ordered {

    private static Logger logger = LoggerFactory.getLogger(CaptchaFilter.class);

    private CaptchaService captchaService;

    private static final String CAPTCHA_HEADER = "captcha";

    private final Collection<String> captchaLinks = new ArrayList<>();


    public CaptchaFilter(CaptchaService captchaService, Collection<String> captchaLinks) {
        this.captchaService = captchaService;
        this.captchaLinks.addAll(captchaLinks);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().value();
        if (captchaLinks.stream().anyMatch(link -> path.equals(link))) {
            String captchaJson = request.getHeaders().getFirst(CAPTCHA_HEADER);
            if (!StrUtils.hasText(captchaJson)) {
                return GatewayUtils.getVoidMono(exchange, new ApiResponseData<>(ResultCode.BAD_REQUEST, "header 缺少验证码", null));
            }
            try {
                CaptchaVO captchaVO = new ObjectMapper().readValue(captchaJson, CaptchaVO.class);
                ResponseModel rm = captchaService.verification(captchaVO);
                if (!rm.isSuccess()) {
                    return GatewayUtils.getVoidMono(exchange, new ApiResponseData<>(ResultCode.BAD_REQUEST, "验证码错误:"+rm.getRepMsg(), null));
                }
            } catch (JsonProcessingException e) {
                String msg = "验证码格式错误:" + e.getMessage();
                logger.error(msg, e);
                return GatewayUtils.getVoidMono(exchange, new ApiResponseData<>(ResultCode.BAD_REQUEST, msg, null));
            }
        }
        return chain.filter(exchange);
    }


    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
package org.exframework.spring.boot.captcha.filter;

import com.anji.captcha.model.common.ResponseModel;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 校验验证码
 *
 * @author rwe
 * @date 2021/9/30 18:50
 **/
public abstract class AbstractCaptchaReactorFilter implements WebFilter, Ordered {

    private static Logger logger = LoggerFactory.getLogger(AbstractCaptchaReactorFilter.class);

    private CaptchaService captchaService;

    private String header;

    private final Collection<String> captchaLinks = new ArrayList<>();


    public AbstractCaptchaReactorFilter(CaptchaService captchaService, Collection<String> captchaLinks) {
        this("captcha", captchaService, captchaLinks);
    }


    public AbstractCaptchaReactorFilter(String header, CaptchaService captchaService, Collection<String> captchaLinks) {
        this.header = header;
        this.captchaService = captchaService;
        this.captchaLinks.addAll(captchaLinks);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().value();
        if (captchaLinks.stream().anyMatch(link -> path.equals(link))) {
            String captchaJson = request.getHeaders().getFirst(header);
            if (!StringUtils.hasText(captchaJson)) {
                return getVoidMono(exchange, "header 缺少验证码");
            }
            try {
                CaptchaVO captchaVO = new ObjectMapper().readValue(captchaJson, CaptchaVO.class);
                ResponseModel rm = captchaService.verification(captchaVO);
                if (!rm.isSuccess()) {
                    return getVoidMono(exchange, "验证码错误:" + rm.getRepMsg());
                }
            } catch (JsonProcessingException e) {
                String msg = "验证码格式错误:" + e.getMessage();
                logger.error(msg, e);
                return getVoidMono(exchange, msg);
            }
        }
        return chain.filter(exchange);
    }

    private Mono<Void> getVoidMono(ServerWebExchange exchange, String message) {
        ServerHttpResponse resp = exchange.getResponse();
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(responseFrom(message));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        DataBuffer buffer = resp.bufferFactory().wrap(json.getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Mono.just(buffer));
    }

    public abstract Object responseFrom(String message);


    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
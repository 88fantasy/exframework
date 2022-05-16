package org.exframework.gateway.sso.filter;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import org.exframework.gateway.config.GatewayBaseProperties;
import org.exframework.gateway.utils.NetworkIpUtils;
import org.exframework.support.rest.entity.ApiResponseData;
import org.exframework.support.rest.enums.ResultCode;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.server.ServerWebExchange;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 默认 SaToken 过滤器
 *
 * @author rwe
 * @date 2022/4/23 00:05
 **/
public class DefaultSaReactorFilter extends SaReactorFilter {

    /**
     * redis中缓存ip鉴权失败次数
     */
    private final String IP_FAIL_COUNT_PREFIX = "requestIp:forbidCount:%s";


    public DefaultSaReactorFilter(GatewayBaseProperties properties, RedisTemplate<String, Object> redisTemplate) {
        // 指定 [拦截路由]
        this.addInclude("/**")
                // 指定 [放行路由]
                .setExcludeList(properties.getGatewayExcludes())
                .addExclude("/favicon.ico", "/**/*.jpg", "/**/*.jpeg", "/**/*.gif", "/**/*.png", "/**/*.ico", "/**/*.cur", "/**/*.gz", "/**/*.svg", "/**/*.svgz", "/**/*.mp4", "/**/*.ogg", "/**/*.ogv", "/**/*.webm", "/**/*.htc", "/**/*.css", "/**/*.js")

                // 指定[异常处理函数]：每次[认证函数]发生异常时执行此函数
                .setError(e -> {
                    // 设置错误返回格式为JSON
                    ServerWebExchange exchange = SaReactorSyncHolder.getContext();
                    exchange.getResponse().getHeaders().set("Content-Type", "application/json; charset=utf-8");
                    // 同一个ip鉴权超过一定次数进行封禁
                    String requestIp = NetworkIpUtils.getIpAddress(exchange.getRequest());
                    String key = String.format(IP_FAIL_COUNT_PREFIX, requestIp);
                    Integer forbidCount = (Integer) redisTemplate.boundValueOps(key).get();
                    if (Objects.nonNull(forbidCount) && forbidCount >= properties.getBlockLimit()) {
                        return new ApiResponseData<>(ResultCode.UNAUTHORIZED, "您的帐号已被锁定，请联系管理员", null);
                    }
                    if (Objects.nonNull(forbidCount) && forbidCount < properties.getBlockLimit()) {
                        forbidCount++;
                        if (forbidCount >= properties.getBlockLimit()) {
                            redisTemplate.boundValueOps(key).set(forbidCount, properties.getBlockTime(), TimeUnit.SECONDS);
                        } else {
                            redisTemplate.boundValueOps(key).set(forbidCount);
                        }
                    } else {
                        redisTemplate.boundValueOps(key).set(1, 60, TimeUnit.SECONDS);
                    }
                    return new ApiResponseData<>(ResultCode.UNAUTHORIZED, e.getMessage(), null);
                })
                .setBeforeAuth(r -> {
                    // ---------- 设置一些安全响应头 ----------
                    SaHolder.getResponse()
                            // 是否可以在iframe显示视图： DENY=不可以 | SAMEORIGIN=同域下可以 | ALLOW-FROM uri=指定域名下可以
                            .setHeader("X-Frame-Options", "SAMEORIGIN")
                            // 是否启用浏览器默认XSS防护： 0=禁用 | 1=启用 | 1; mode=block 启用, 并在检查到XSS攻击时，停止渲染页面
                            .setHeader("X-XSS-Protection", "1; mode=block")
                            // 禁用浏览器内容嗅探
                            .setHeader("X-Content-Type-Options", "nosniff")
                    ;
                })
        ;
    }

}

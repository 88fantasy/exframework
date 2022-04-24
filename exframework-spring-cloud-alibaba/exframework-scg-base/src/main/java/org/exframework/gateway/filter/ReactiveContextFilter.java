package org.exframework.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * webflux 上下文
 *
 * @author: rwe
 * @version: 1.0
 * @date: 2021/11/10
 */
public class ReactiveContextFilter implements GlobalFilter, Ordered {

    private static Logger log = LoggerFactory.getLogger(ReactiveContextFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();
        return chain.filter(exchange).contextWrite(ctx -> ctx.put(ReactiveRequestContextHolder.REQUEST_KEY, request).put(ReactiveRequestContextHolder.RESPONSE_KEY, response));
    }


    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

    public static class ReactiveRequestContextHolder {

        public static final Class<ServerHttpRequest> REQUEST_KEY = ServerHttpRequest.class;
        public static final Class<ServerHttpResponse> RESPONSE_KEY = ServerHttpResponse.class;

        public static Mono<ServerHttpRequest> getRequest() {
            return Mono.deferContextual(Mono::just)
                    .map(ctx -> ctx.get(REQUEST_KEY));
        }

        public static Mono<ServerHttpResponse> getResponse() {
            return Mono.deferContextual(Mono::just)
                    .map(ctx -> ctx.get(RESPONSE_KEY));
        }
    }
}

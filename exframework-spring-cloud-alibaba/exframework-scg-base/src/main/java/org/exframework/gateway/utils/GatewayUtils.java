package org.exframework.gateway.utils;

import cn.hutool.json.JSONUtil;
import org.exframework.support.rest.entity.ApiResponseData;
import org.exframework.support.rest.enums.ResultCode;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;


/**
 * 回写消息
 *
 * @author: rwe
 * @version: 1.0
 * @date: 2022/2/20
 */
public class GatewayUtils {

    public static Mono<Void> writeResponse(ServerWebExchange exchange, ResultCode resultCode) {
        ServerHttpResponse resp = exchange.getResponse();
        resp.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

        return getVoidMono(exchange, resp, new ApiResponseData<String>(resultCode, null));
    }

    public static Mono<Void> getVoidMono(ServerWebExchange exchange, ServerHttpResponse resp, ApiResponseData<?> response) {
        DataBuffer buffer = resp.bufferFactory().wrap(JSONUtil.toJsonStr(response).getBytes(StandardCharsets.UTF_8));
        return resp.writeWith(Mono.just(buffer));
    }

}

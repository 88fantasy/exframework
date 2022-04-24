package org.exframework.gateway.filter;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.alibaba.csp.sentinel.util.StringUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.exframework.gateway.utils.NetworkIpUtils;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR;

/**
 * @author 郑剑锋
 * @Description 全局拦截器，作用所有的微服务
 * 1. 对请求的API调用过滤，记录接口的请求时间，方便日志审计、告警、分析等运维操作
 * 2. 后期可以扩展对接其他日志系统
 * @createTime 2022.02.24 18:31:00
 */
public abstract class AbstractApiLoggingFilter implements GlobalFilter, Ordered {

    private final Logger log = LoggerFactory.getLogger(AbstractApiLoggingFilter.class);

    private static final String START_TIME = "startTime";
    private static final String RESPONSE_BODY = "responseBody";
    private static final String RESPONSE_HEADER = "responseHeader";

    private static List<ModifyResponseBodyRule> staticRules;

    public abstract List<ModifyResponseBodyRule> getStaticRules();

    public abstract void saveGatewayLog(GatewayLog log);

    @PostConstruct
    public void staticVarAssignment() {
        staticRules = getStaticRules();
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        if (log.isDebugEnabled()) {
            log.debug(String.format("Method:{%s} Host:{%s} Path:{%s} Query:{%s}", exchange.getRequest().getMethod().name(), exchange.getRequest().getURI().getHost(), exchange.getRequest().getURI().getPath(), exchange.getRequest().getQueryParams()));
        }
        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());
        return chain.filter(exchange.mutate().response(new ModifyServerHttpResponseBody(exchange)).build()).doOnError(e -> {

            exchange.getAttributes().put(RESPONSE_BODY, ExceptionUtil.stacktraceToString(e));
        }).doFinally(signalType -> log(exchange, signalType));
    }

    private void log(ServerWebExchange exchange, SignalType signalType) {
        Long startTime = exchange.getAttribute(START_TIME);
        if (startTime != null) {
            Long executeTime = (System.currentTimeMillis() - startTime);
            String ip = NetworkIpUtils.getIpAddress(exchange.getRequest());
            String api = exchange.getRequest().getURI().getRawPath();
            int code = exchange.getResponse().getStatusCode() != null ? exchange.getResponse().getStatusCode().value() : 500;
            String uri = exchange.getRequest().getURI().getPath();
            String params = JSON.toJSONString(exchange.getRequest().getQueryParams());
            Map<String, Object> attributes = exchange.getAttributes();
            // 当前仅记录日志，后续可以添加日志队列，来过滤请求慢的接口
            if (log.isDebugEnabled()) {
                log.debug("信号类型: {},来自IP地址：{}的请求接口：{}，响应状态码：{}，请求耗时：{}ms", signalType, ip, api, code, executeTime);
            }
            GatewayLog gatewayLogDTO = new GatewayLog().setRequestIp(ip).setExecTime(executeTime).setUri(uri).setStatusCode(code).setParams(params)
                    .setResponseBody((String) attributes.get(RESPONSE_BODY));
            saveGatewayLog(gatewayLogDTO);
        }
    }

    /**
     * 根据关键字替换响应报文
     */
    static class ModifyServerHttpResponseBody extends ServerHttpResponseDecorator {

        private final ServerWebExchange exchange;

        public ModifyServerHttpResponseBody(final ServerWebExchange exchange) {
            super(exchange.getResponse());
            this.exchange = exchange;
        }

        @Override
        public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
            if (body instanceof Flux) {
                String originalResponseContentType = exchange.getAttribute(ORIGINAL_RESPONSE_CONTENT_TYPE_ATTR);
                if (StringUtil.isNotBlank(originalResponseContentType) && originalResponseContentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
                    Flux<? extends DataBuffer> fluxBody = (Flux<? extends DataBuffer>) body;
                    return super.writeWith(fluxBody.buffer().map(dataBuffer -> {
                        DataBufferFactory dataBufferFactory = new DefaultDataBufferFactory();
                        DataBuffer join = dataBufferFactory.join(dataBuffer);
                        byte[] content = new byte[join.readableByteCount()];
                        join.read(content);
                        //释放掉内存
                        DataBufferUtils.release(join);
                        String responseBody = new String(content, StandardCharsets.UTF_8);
                        Map<String, Object> attributes = exchange.getAttributes();
                        attributes.put(RESPONSE_BODY, responseBody);
                        attributes.put(RESPONSE_HEADER, JSON.toJSONString(this.getDelegate().getHeaders()));
                        String newResponseBody = responseBody;
                        try {
                            JSONObject obj = JSON.parseObject(responseBody);
                            //仅当错误请求进行替换
                            if (!obj.getBoolean("ok")) {
                                String data = obj.getString("data");
                                Optional<ModifyResponseBodyRule> rule = staticRules.stream().filter(r -> data.matches(r.getRegex())).findFirst();
                                if (rule.isPresent()) {
                                    obj.put("data", rule.get().getBody());
                                }
                            }
                            newResponseBody = obj.toJSONString();
                        } catch (Exception e) {
                        }
                        byte[] responseBodyBytes = newResponseBody.getBytes(StandardCharsets.UTF_8);
                        exchange.getResponse().getHeaders().setContentLength(responseBodyBytes.length);
                        return exchange.getResponse().bufferFactory().wrap(responseBodyBytes);
                    }));
                }
            }
            // if body is not a flux. never got there.
            return super.writeWith(body);
        }
    }


    @Override
    public int getOrder() {
        return -2;
    }


    public class GatewayLog {

        /**
         * ip地址
         */
        private String requestIp;

        /**
         * 请求路径
         */
        private String uri;

        /**
         * 请求参数
         */
        private String params;

        /**
         * 请求状态码
         */
        private Integer statusCode;

        /**
         * 响应体
         */
        private String responseBody;

        /**
         * 执行时间
         */
        private Long execTime;

        public String getRequestIp() {
            return requestIp;
        }

        public GatewayLog setRequestIp(String requestIp) {
            this.requestIp = requestIp;
            return this;
        }

        public String getUri() {
            return uri;
        }

        public GatewayLog setUri(String uri) {
            this.uri = uri;
            return this;
        }

        public String getParams() {
            return params;
        }

        public GatewayLog setParams(String params) {
            this.params = params;
            return this;
        }

        public Integer getStatusCode() {
            return statusCode;
        }

        public GatewayLog setStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public String getResponseBody() {
            return responseBody;
        }

        public GatewayLog setResponseBody(String responseBody) {
            this.responseBody = responseBody;
            return this;
        }

        public Long getExecTime() {
            return execTime;
        }

        public GatewayLog setExecTime(Long execTime) {
            this.execTime = execTime;
            return this;
        }
    }

    public class ModifyResponseBodyRule implements Serializable {

        private String regex;
        private String body;

        public ModifyResponseBodyRule() {
        }

        public String getRegex() {
            return regex;
        }

        public ModifyResponseBodyRule setRegex(String regex) {
            this.regex = regex;
            return this;
        }

        public String getBody() {
            return body;
        }

        public ModifyResponseBodyRule setBody(String body) {
            this.body = body;
            return this;
        }
    }
}
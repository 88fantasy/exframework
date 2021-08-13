package org.exframework.tsf.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.web.server.ServerWebExchange;

import com.tencent.tsf.gateway.core.annotation.TsfGatewayFilter;
import com.tencent.tsf.gateway.scg.AbstractTsfGlobalFilter;

import reactor.core.publisher.Mono;

/**
 * 网关过滤器示例 
 * 
 * @author rwe Date: 2021年3月25日
 *
 * Copyright @ 2021
 * 
 */
@TsfGatewayFilter
public class DemoFilter extends AbstractTsfGlobalFilter {

	private static final Logger logger = LoggerFactory.getLogger(DemoFilter.class);

	@Override
	public Mono<Void> doFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("hello world");
        return chain.filter(exchange);
	}

	@Override
	public int getOrder() {
		return 100;
	}

	@Override
	public boolean shouldFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
		return true;
	}

}

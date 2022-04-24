package org.exframework.gateway.handler;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.util.function.Supplier;
import org.exframework.gateway.utils.GatewayUtils;
import org.exframework.support.rest.enums.ResultCode;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebExceptionHandler;
import reactor.core.publisher.Mono;

import java.util.List;


/**
 * Sentinel 限流后自定义异常
 * @author: lls
 * @version: 1.0
 * @date: 2021/3/31
 */
public class SentinelBlockExceptionHandler implements WebExceptionHandler {

	private List<ViewResolver> viewResolvers;
	private List<HttpMessageWriter<?>> messageWriters;

	public SentinelBlockExceptionHandler(List<ViewResolver> viewResolvers,
                                         ServerCodecConfigurer serverCodecConfigurer) {
		this.viewResolvers = viewResolvers;
		this.messageWriters = serverCodecConfigurer.getWriters();
	}

	@Override
	public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
		ex.printStackTrace();

		ResultCode resultCode = ResultCode.BAD_GATEWAY;

		if (ex instanceof FlowException) {

//		} else if (ex instanceof DegradeException) {
//
//		} else if (ex instanceof ParamFlowException) {
//
//		} else if (ex instanceof SystemBlockException) {
//
		} else if (ex instanceof AuthorityException) {
			resultCode = ResultCode.UNAUTHORIZED;

		}else if(ex instanceof NotFoundException){
			resultCode = ResultCode.NOT_FOUND;
		}
		
		Mono<Void> mono = GatewayUtils.writeResponse(exchange, resultCode);
		
		return GatewayCallbackManager.getBlockHandler().handleRequest(exchange, ex).flatMap(response -> mono);
	}

	@SuppressWarnings("unused")
	private final Supplier<ServerResponse.Context> contextSupplier = () -> new ServerResponse.Context() {
		@Override
		public List<HttpMessageWriter<?>> messageWriters() {
			return SentinelBlockExceptionHandler.this.messageWriters;
		}

		@Override
		public List<ViewResolver> viewResolvers() {
			return SentinelBlockExceptionHandler.this.viewResolvers;
		}
	};
}
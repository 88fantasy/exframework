package com.gzmpc.microservice.demo.proxy.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.gzmpc.microservice.demo.proxy.DemoProxy;

import feign.hystrix.FallbackFactory;

/**
* @author rwe
* @version 创建时间：Oct 18, 2020 10:18:35 AM
* ConfigProxy 错误处理
*/

@Component
public class DemoProxyFallback implements FallbackFactory<DemoProxy> {

	private static final Logger LOG = LoggerFactory.getLogger(DemoProxyFallback.class);
	
	@Override
	public DemoProxy create(Throwable cause) {
		String msg = cause == null ? "" : cause.getMessage();
    LOG.error("DemoProxy接口不可用,错误处理接管:"+msg, cause);
		return new DemoProxy() {
			
		};
	}

}

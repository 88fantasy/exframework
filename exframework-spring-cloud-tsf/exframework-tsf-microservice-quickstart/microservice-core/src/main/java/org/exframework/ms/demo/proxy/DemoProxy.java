package org.exframework.ms.demo.proxy;

import org.springframework.cloud.openfeign.FeignClient;

import org.exframework.ms.demo.proxy.fallback.DemoProxyFallback;

/**
 * 微服务 proxy类
 * @author pro
 *
 */
@FeignClient(name = "microservice-provider", fallbackFactory = DemoProxyFallback.class)
public interface DemoProxy {

}

package org.exframework.alibaba.ms.quickstart.proxy;

import org.exframework.alibaba.ms.quickstart.proxy.fallback.DemoProxyFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 微服务 proxy类
 * @author pro
 *
 */
@FeignClient(name = "microservice-provider", fallback = DemoProxyFallback.class, configuration = {})
public interface DemoProxy {

}

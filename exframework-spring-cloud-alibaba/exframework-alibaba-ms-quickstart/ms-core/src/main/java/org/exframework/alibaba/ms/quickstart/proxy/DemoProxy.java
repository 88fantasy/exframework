package org.exframework.alibaba.ms.quickstart.proxy;

import com.alibaba.cloud.dubbo.annotation.DubboTransported;
import org.exframework.alibaba.ms.quickstart.config.FeignHeaderConfiguration;
import org.exframework.alibaba.ms.quickstart.proxy.fallback.DemoProxyFallback;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 微服务 proxy类
 * @author pro
 *
 */
@FeignClient(name = "microservice-provider", fallback = DemoProxyFallback.class, configuration = {FeignHeaderConfiguration.class})
@DubboTransported
public interface DemoProxy {

}

# exframework

新框架-Spring Cloud Gateway (网关路由)-脚手架

Maven : exframework-springcloud-gateway-quickstart

##  实现方式

在原生 scgw 基础上通过 `Redis` 和 `SpringCloudBus(消息总线)` 进行动态路由配置
当通过界面系统触发路由刷新指令后, 系统会通过数据库加载数据到`Redis`中,然后调用消息总线执行广播,通知所有网关实例进行刷新


## 使用方式

1. 修改 redis 配置
2. 修改 rabbitmq 配置
3. 根据需要修改 application.yml 中的 spring.cloud.bus.destination 总线 topic 以防队列冲突
4. {pathto}.config.BusConfiguration 中  GatewayRouteEvent  通常在 common 包 , 代码参考

```java
package com.gzmpc.business.developer.common.bus;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

/**
* @author rwe
* @version 创建时间：2021年3月28日 下午5:26:50
* 类说明
*/

public class GatewayRouteEvent extends RemoteApplicationEvent {

	private static final long serialVersionUID = -968108352908735527L;

	public GatewayRouteEvent() {
		super();
	}

	public GatewayRouteEvent(String source, String originService, String destinationService) {
		super(source, originService, destinationService);
	}

	public GatewayRouteEvent(String source, String originService) {
		super(source, originService);
	}

}
```

## 注意

本脚手架基于 scgw 原生实现, 如果需要结合 tsf 框架使用请移步至  exframework-tsf-gateway-quickstart

>本地运行需要设置 TSF 信息
-Dtsf_token=111
-Dtsf_namespace_id=ggggga
-Dtsf_application_id=aaaaaa
-Dtsf_app_id=111
-Dtsf_group_id=group-1


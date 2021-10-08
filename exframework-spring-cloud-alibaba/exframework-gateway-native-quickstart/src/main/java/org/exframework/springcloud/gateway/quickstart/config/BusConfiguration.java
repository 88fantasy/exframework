package org.exframework.springcloud.gateway.quickstart.config;

import org.exframework.springcloud.gateway.quickstart.service.GatewayServiceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;


/**
* @author rwe
* @version 创建时间：2021年3月28日 下午5:33:01
* 类说明
*/

@Configuration
//@RemoteApplicationEventScan(basePackageClasses = GatewayRouteEvent.class)
public class BusConfiguration {
	
	private static final Logger logger = LoggerFactory.getLogger(BusConfiguration.class);
	
	@Autowired
	GatewayServiceHandler gatewayServiceHandler;

//	@EventListener
//  public void onGatewayRemoteApplicationEvent(GatewayRouteEvent event) {
//			logger.info("刷新路由 originService: {}, destinationService: {} ", event.getOriginService(), event.getDestinationService());
//      gatewayServiceHandler.loadRouteConfig();
//  }
}

package com.gzmpc.tsf.gateway.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.gzmpc.tsf.gateway.entity.GatewayRoute;
import com.gzmpc.tsf.gateway.route.RedisRouteDefinitionRepository;

import reactor.core.publisher.Mono;

/**
 *
 * Author: rwe
 * Date: 2021年3月25日
 *
 * Copyright @ 2021 
 * 
 */
@Service
public class GatewayServiceHandler implements ApplicationEventPublisherAware, CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(GatewayServiceHandler.class);

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
    private RedisRouteDefinitionRepository routeDefinitionWriter;
	
    private ApplicationEventPublisher publisher;
    
    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }

	@Override
	public void run(String... args) throws Exception {
		this.loadRouteConfig();
	}
	
	public String loadRouteConfig() {
		logger.info("------------开始加载网关配置信息------------");
        
        publisher.publishEvent(new RefreshRoutesEvent(this));
        
        logger.info("------------网关配置信息加载完成----------");
        return "success";
    }
    
}

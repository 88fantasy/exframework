package com.gzmpc.tsf.gateway.route;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 从 redis 同步路由表
 * Author: rwe Date: 2021年3月25日
 *
 * Copyright @ 2021
 * 
 */
@Component
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisRouteDefinitionRepository.class);

	public static final String GATEWAY_ROUTES = "gateway:routes";

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public Flux<RouteDefinition> getRouteDefinitions() {
		List<RouteDefinition> routeDefinitions = new ArrayList<>();
		redisTemplate.opsForHash().values(GATEWAY_ROUTES).stream().forEach(routeDefinition -> {
			routeDefinitions.add(JSON.parseObject(routeDefinition.toString(), RouteDefinition.class));
		});
		return Flux.fromIterable(routeDefinitions);
	}

	@Override
	public Mono<Void> save(Mono<RouteDefinition> route) {
		return route.flatMap(routeDefinition -> {
			redisTemplate.opsForHash().put(GATEWAY_ROUTES, routeDefinition.getId(), JSON.toJSONString(routeDefinition));
			return Mono.empty();
		});
	}

	@Override
	public Mono<Void> delete(Mono<String> routeId) {
		return routeId.flatMap(id -> {
			if (redisTemplate.opsForHash().hasKey(GATEWAY_ROUTES, id)) {
				redisTemplate.opsForHash().delete(GATEWAY_ROUTES, id);
			}
			else {
				logger.error("路由文件没有找到: " + routeId);
			}
			return Mono.empty();
		});
	}

}

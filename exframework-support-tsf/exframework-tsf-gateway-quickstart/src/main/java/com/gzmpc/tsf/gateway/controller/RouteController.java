package com.gzmpc.tsf.gateway.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gzmpc.tsf.gateway.service.GatewayServiceHandler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * Author: rwe
 * Date: 2021年3月25日
 *
 * Copyright @ 2021 
 * 
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600, methods = { RequestMethod.OPTIONS, RequestMethod.POST })
@RequestMapping("/route")
public class RouteController {
	
	@Autowired
	private GatewayServiceHandler gatewayServiceHandler;

	
	@GetMapping("/refresh")
	public String refresh() throws Exception {
		return gatewayServiceHandler.loadRouteConfig();
	}
}

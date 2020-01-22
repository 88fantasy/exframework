package com.gzmpc.springboot.dubbo.provider.service;


import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.gzmpc.springboot.dubbo.consume.service.HelloService;


/**
* @author rwe
* @version 创建时间：2018年4月19日 上午11:41:13
* 类说明
*/

@Component
@Service(version = "${demo.service.version}")
public class HelloServiceImpl implements HelloService {
	
	/**
     * The default value of ${dubbo.application.name} is ${spring.application.name}
     */
    @Value("${dubbo.application.name}")
    private String serviceName;


    @Override
    public String sayHello(String name) {
        return String.format("Hello, %s",  name);
    }

}

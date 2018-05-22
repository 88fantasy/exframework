package com.gzmpc.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
* @author rwe
* @version 创建时间：2018年5月13日 下午3:42:36
* 类说明
*/

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Configuration
public class AspectConfiguration {

}

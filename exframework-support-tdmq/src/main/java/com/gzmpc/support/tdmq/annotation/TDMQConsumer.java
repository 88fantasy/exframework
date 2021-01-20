package com.gzmpc.support.tdmq.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.stereotype.Service;


/**
* @author rwe
* @version 创建时间：Nov 6, 2020 5:39:30 PM
* tdmq 消费者
* 需要实现 org.apache.pulsar.client.api.MessageListener
*/

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface TDMQConsumer {

	String value();
	
	String subscriptionName();
	
	SubscriptionType subscriptionType() default SubscriptionType.Failover;
	
}

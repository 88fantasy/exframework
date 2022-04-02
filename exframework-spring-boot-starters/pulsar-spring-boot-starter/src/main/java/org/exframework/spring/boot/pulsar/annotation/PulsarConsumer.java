package org.exframework.spring.boot.pulsar.annotation;

import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;


/**
* @author rwe
* @version 创建时间：Nov 6, 2020 5:39:30 PM
* Pulsar 消费者
* 需要实现 org.apache.pulsar.client.api.MessageListener
*/

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Service
public @interface PulsarConsumer {

	String value();
	
	String subscriptionName() default "";
	
	SubscriptionType subscriptionType() default SubscriptionType.Failover;
	
}

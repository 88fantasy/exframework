package com.gzmpc.support.tdmq.listener;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.MessageListener;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.client.api.SubscriptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.gzmpc.support.tdmq.annotation.TDMQConsumer;


/**
 * @author rwe
 * @version 创建时间：Oct 16, 2020 2:11:05 PM 自动创建 TDMQ 消费者
 */
@Repository
public class TDMQInitListener implements ApplicationListener<ApplicationReadyEvent>, DisposableBean {
	
	@Autowired 
	private Environment env;

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	List<Consumer<String>> consumers = new ArrayList<Consumer<String>>();


	PulsarClient client;
	
	@Resource(name = "defaultPulsarClient")
	public void setClient(PulsarClient client) {
		this.client = client;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		ConfigurableApplicationContext ac = event.getApplicationContext();
		log.info("开始加载[自动创建TDMQ消费者]注解");
		
		if( client == null) {
			log.warn("初始化 TDMS 队列失败");
			return;
		}

		Map<String, Object> beans = ac.getBeansWithAnnotation(TDMQConsumer.class);
		for (String name : beans.keySet()) {
			Object o = beans.get(name);
			if (o != null) {
				if (o instanceof MessageListener) { // 判断是否实现了接口
					String className = o.getClass().getName();
					log.info("加载[" + className + "]开始");
					TDMQConsumer[] defines = o.getClass().getAnnotationsByType(TDMQConsumer.class);
					if (defines != null && defines.length > 0) {

						for (TDMQConsumer define : defines) {
							String spel = define.value();
							String topic = env.getProperty(spel);
							if (StringUtils.hasText(topic)) {
								String topicName = MessageFormat.format("persistent://{0}", topic);
								String subscriptionName = define.subscriptionName();
								if (!StringUtils.hasText(subscriptionName)) {
									subscriptionName = className;
								}
								SubscriptionType type = define.subscriptionType();

								@SuppressWarnings("unchecked")
								MessageListener<String> listener = (MessageListener<String>) o;
								if (listener != null && client != null) {
									Consumer<String> consumer = null;
									try {
										consumer = client.newConsumer(Schema.STRING)
												.topic(topicName)
												.messageListener(listener)
												.subscriptionName(subscriptionName)
												.subscriptionType(type)
												.subscribe();

									} catch (PulsarClientException e) {
										log.error(MessageFormat.format("创建队列[{0}]失败:{1}", topicName, e.getMessage()), e);
									} finally {
										if (consumer != null) {
											consumers.add(consumer);
										}
									}
								} else {
									log.error(MessageFormat.format("{0} can not cast to org.apache.pulsar.client.api.MessageListener<String> ",
													o.getClass().getName()));
								}
							} else {
								log.error(MessageFormat.format("{0} 缺失 value", o.getClass().getName()));
							}
						}

					}
				}
			}
		}
		log.info("加载[自动创建TDMQ消费者]注解结束");
	}

	@Override
	public void destroy() throws Exception {
		for(Consumer<String> consumer : consumers) {
			try {
				consumer.close();
			}
			catch (PulsarClientException e) {
				log.error(MessageFormat.format("关闭消费者[{0}]出现错误:", consumer, e.getMessage()), e);
			}
		}
	}
}

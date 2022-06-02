package org.exframework.spring.boot.pulsar.factory;
/**
 * @author rwe
 * @since Jan 22, 2021
 * <p>
 * Copyright @ 2021
 */

import org.apache.pulsar.client.api.*;
import org.exframework.spring.boot.pulsar.annotation.PulsarConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;

import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.Map;

public class PulsarProducerFactory {

    private final Logger log = LoggerFactory.getLogger(PulsarProducerFactory.class);

    private final Map<String, Producer> producers = new LinkedHashMap<>();

    private final ApplicationContext applicationContext;

    private final Environment env;

    public PulsarProducerFactory(ApplicationContext applicationContext, Environment env) {
        this.applicationContext = applicationContext;
        this.env = env;
    }

    public void register(String broker, PulsarClient client) {
        Map<String, Object> beans = applicationContext.getBeansWithAnnotation(PulsarConsumer.class);
        for (String name : beans.keySet()) {
            Object o = beans.get(name);
            if (o != null && MessageListener.class.isAssignableFrom(o.getClass())) {
                String className = o.getClass().getName();
                log.info("加载[" + className + "]开始");
                PulsarConsumer define = o.getClass().getAnnotation(PulsarConsumer.class);
                String b = define.broker();
                if (!b.equals(broker)) {
                    continue;
                }
                String spel = define.value();
                String topic = env.getProperty(spel);
                String topicName = MessageFormat.format("persistent://{0}", topic);
                String subscriptionName = define.subscriptionName();
                if (!StringUtils.hasText(subscriptionName)) {
                    subscriptionName = className;
                }
                SubscriptionType type = define.subscriptionType();

                @SuppressWarnings("unchecked")
                MessageListener<String> listener = (MessageListener<String>) o;
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
                        producers.putIfAbsent(b, new Producer(consumer, client));
                    }
                }
            }
        }
        log.info("加载[自动创建Pulsar消费者]注解结束");
    }

    public Producer get(String tunnel) {
        if (!producers.containsKey(tunnel)) {
            throw new RuntimeException("缺少 tunnel[" + tunnel + "] 实现");
        }
        return producers.get(tunnel);
    }


    public <T> org.apache.pulsar.client.api.Producer<T> make(PulsarClient client, Schema<T> schema, String topic) throws PulsarClientException {
        return client.newProducer(schema).topic(topic).create();
    }

    public org.apache.pulsar.client.api.Producer<byte[]> make(PulsarClient client, String topic) throws PulsarClientException {
        return client.newProducer().topic(topic).create();
    }


    public class Producer {

        private Consumer consumer;

        private PulsarClient client;

        public Producer(Consumer consumer, PulsarClient client) {
            this.consumer = consumer;
            this.client = client;
        }

        public Consumer getConsumer() {
            return consumer;
        }

        public PulsarClient getClient() {
            return client;
        }
    }
}

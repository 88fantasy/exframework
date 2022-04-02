package org.exframework.spring.boot.pulsar.factory;
/**
 *
 * @author rwe
 * @since Jan 22, 2021
 *
 * Copyright @ 2021 
 * 
 */

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PulsarProducerFactory {

	@Autowired
	PulsarClient defaultPulsarClient;
	
	public <T> Producer<T> make(Schema<T> schema, String topic) throws PulsarClientException {
		return defaultPulsarClient.newProducer(schema).topic(topic).create();
	}
	
	public Producer<byte[]> make(String topic) throws PulsarClientException {
		return defaultPulsarClient.newProducer().topic(topic).create();
	}
}

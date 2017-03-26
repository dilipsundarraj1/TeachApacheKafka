package com.learnkafka.paritioner;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;

public class ProducerKafkaPartitioner implements Partitioner {

	public void configure(Map<String, ?> configs) {
		// TODO Auto-generated method stub
		
	}

	public int partition(String topic, Object key, byte[] keyBytes,
			Object value, byte[] valueBytes, Cluster cluster) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void close() {
		// TODO Auto-generated method stub
		
	}

}

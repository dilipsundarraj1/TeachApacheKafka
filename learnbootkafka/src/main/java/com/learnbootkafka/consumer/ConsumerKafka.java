package com.learnbootkafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

public class ConsumerKafka {

	
	 @KafkaListener(id = "consumer", topics = {"${kafka.topic}"} )
	 public void onMessage(ConsumerRecord<?, ?> record) {
		 
		 System.out.println("Read Record is : " + record.value());
		 
	 }
}

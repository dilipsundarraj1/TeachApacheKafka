package com.learnkafka.consumer;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumerKafkaSSL {

public static void main(String[] args) {
		
		Properties properties=new Properties();
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("group.id","test2");
		properties.put("security.protocol","SSL");
		properties.put("ssl.truststore.location","<path>/server.truststore.jks");
		properties.put("ssl.truststore.password","kafka123");
		properties.put("ssl.keystore.location","<path>/server.keystore.jks");
		properties.put("ssl.keystore.password","kafka123");
		properties.put("ssl.key.password","kafka123");
		
		KafkaConsumer< String, String> consumer=null;
		
		try {
		ArrayList<String> topics=new ArrayList<String>();
		topics.add("my-ssl-topic");
		consumer = new KafkaConsumer<String, String>(properties);
		consumer.subscribe(topics); // You can subscribe to any number of topics.
		
		
			
			while(true){
				
				ConsumerRecords<String, String> records = consumer.poll(1000);
				
				for(ConsumerRecord<String, String> record : records){
					
					System.out.println("Record read in KafkaConsumerApp : " +  record.toString());
					
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Inside exception loop : ");
			e.printStackTrace();
		}finally{
			consumer.close();
		}
	}
}

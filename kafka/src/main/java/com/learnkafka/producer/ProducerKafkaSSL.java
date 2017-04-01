package com.learnkafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class ProducerKafkaSSL {
	public static void main(String[] args) {
		Properties properties=new Properties();
		properties.put("bootstrap.servers", "localhost:9092");
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("security.protocol","SSL");
		properties.put("ssl.truststore.location","<path>/server.truststore.jks");
		properties.put("ssl.truststore.password","kafka123");
		properties.put("ssl.keystore.location","<path>/server.keystore.jks");
		properties.put("ssl.keystore.password","kafka123");
		properties.put("ssl.key.password","kafka123");
		
		KafkaProducer<String,String> myProducer= new KafkaProducer<String,String>(properties);
		
			try {
			
			for(int i=1;i<2;i++){
				myProducer.send(new  ProducerRecord<String, String>("my-ssl-topic", "Message Value : " + Integer.toString(i)));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			myProducer.close();
		}
	}
}

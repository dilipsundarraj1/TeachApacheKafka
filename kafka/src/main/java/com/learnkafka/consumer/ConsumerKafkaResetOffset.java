package com.learnkafka.consumer;

import java.util.ArrayList;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

public class ConsumerKafkaResetOffset {
	
	public static void main(String[] args) {

		Properties properties=new Properties();
		properties.put("bootstrap.servers", "localhost:9092,localhost:9093");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("group.id","test1");

		KafkaConsumer< String, String> consumer=new KafkaConsumer<String, String>(properties);

		String topic = "demo-reset-topic";
		

		ArrayList<String> topics=new ArrayList<String>();
		topics.add(topic);

		consumer.subscribe(topics); // You can subscribe to any number of topics.
		
//		TopicPartition parition = new TopicPartition(topic, 0);
//		boolean flag=false;
		try {

			while(true){

				ConsumerRecords<String, String> records = consumer.poll(1000);
				
//				if(!flag){
//					consumer.seek(parition, 0);
//					flag = true;
//				}

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

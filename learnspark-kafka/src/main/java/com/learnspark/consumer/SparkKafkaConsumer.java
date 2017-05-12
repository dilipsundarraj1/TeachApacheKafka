package com.learnspark.consumer;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import scala.Tuple2;

public class SparkKafkaConsumer {
	
	public static void main(String[] args) {
		
		SparkConf sparkConf = new SparkConf().setMaster("local[2]").setAppName("KafkaConsumer"); // Initialize the Spark Context


		JavaStreamingContext javaSC = new JavaStreamingContext(sparkConf, Durations.seconds(10)); // Interval to stream the value from topic. Poll for records every 10 seconds.
		
		Map<String, Object> kafkaParams = new HashMap<String, Object>();
		kafkaParams.put("bootstrap.servers", "localhost:9092");
		kafkaParams.put("key.deserializer", StringDeserializer.class);
		kafkaParams.put("value.deserializer", StringDeserializer.class);
		kafkaParams.put("group.id", "group1");
		kafkaParams.put("auto.offset.reset", "earliest");
		kafkaParams.put("enable.auto.commit", true);

		Collection<String> topics = Arrays.asList("my-topic");

		final JavaInputDStream<ConsumerRecord<String, String>> stream =
		  KafkaUtils.createDirectStream(
				  javaSC,
		    LocationStrategies.PreferConsistent(),
		    ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams)
		  );
		
		stream.foreachRDD(rdd -> {
		    System.out.println("---RDD with " + rdd.partitions().size()
		            + " partitions and " + rdd.count() + " records");
		    rdd.foreach(record -> System.out.println(record.value()));
		});
		
		System.out.println("Before Start");
		javaSC.start();
		System.out.println("After Start");
		 try {
			 javaSC.awaitTermination();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}

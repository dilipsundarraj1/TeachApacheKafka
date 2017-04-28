package com.learnbootkafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.AlwaysRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import com.learnbootkafka.consumer.ConsumerKafka;

@Configuration
@EnableKafka
public class KafkaConfig {
	
	 @Autowired
	  Environment env;
	
	 /**
	  * Consumer Config Starts
	  */
	 @Bean
	 KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
	      ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
	      factory.setConsumerFactory(consumerFactory());
	      factory.getContainerProperties().setPollTimeout(3000);
	      factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL);
	       return factory;
	    }
	
	 @Bean
	    public ConsumerFactory<String, String> consumerFactory() {

	        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	    }
	 
	 @Bean
	    public Map<String, Object> consumerConfigs() {
	        Map<String, Object> propsMap = new HashMap<>();
	        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("kafka.broker"));
	        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, env.getProperty("enable.auto.commit"));
	        propsMap.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, env.getProperty("auto.commit.interval.ms"));
	        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, env.getProperty("group.id"));
	        propsMap.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, env.getProperty("kafka.auto.offset.reset"));
	        return propsMap;

	    }
	 
	 @Bean
	    public ConsumerKafka listener() {
	        return new ConsumerKafka();
	    }
	 
	 /**
	  * Consumer Config Ends
	  */
	
	 /**
	  * Producer Config Starts
	  * @return
	  */
	 
	 @SuppressWarnings("rawtypes")
	@Bean
	    public ProducerFactory producerFactory() {
	        return new DefaultKafkaProducerFactory<>(producerConfigs());
	    }

	    @Bean
	    public KafkaTemplate kafkaTemplate() {
	        return new KafkaTemplate(producerFactory());
	    }
	    /**
		  * Producer Config Ends
		  * @return
		  */
	    
	    @Bean
	    public Map producerConfigs() {
	    	Map<String, Object> props = new HashMap<>();
	    	 props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, env.getProperty("kafka.broker"));
	    	 props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	         props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	         props.put(ProducerConfig.LINGER_MS_CONFIG, 1);

	         
	    	return props;
	    }

}

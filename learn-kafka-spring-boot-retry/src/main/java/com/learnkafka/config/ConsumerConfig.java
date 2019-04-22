package com.learnkafka.config;

import com.learnkafka.consumer.ConsumerRetryListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.AlwaysRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
public class ConsumerConfig {

    @Autowired
    ConsumerRetryListener consumerRetryListener;

    @Value("${spring.kafka.retry.backoff.initial-interval}")
    private Long initialBackoffInterval;

    @Value("${spring.kafka.retry.backoff.max-interval}")
    private Long backOffMaxInterval;

    @Value("${spring.kafka.retry.generate-alert-retry-threshold}")
    private Integer maxRetries;


    @Bean
    ConcurrentKafkaListenerContainerFactory<Object,Object>
    kafkaListenerContainerFactory(ConcurrentKafkaListenerContainerFactoryConfigurer configurer, ConsumerFactory<Object,Object> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> containerFactory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(containerFactory,  consumerFactory);
        containerFactory.setRetryTemplate(retryTemplate()); // This enables the retry.
        return containerFactory;
    }

    @Bean
    public RetryTemplate retryTemplate() {
        SimpleRetryPolicy simpleRetryPolicy = getRetryPolicy();
        FixedBackOffPolicy fixedBackOffPolicy = getBackOffPolicy();
        RetryTemplate retryTemplate = new RetryTemplate();
        retryTemplate.setRetryPolicy(simpleRetryPolicy);
        retryTemplate.setBackOffPolicy(fixedBackOffPolicy);
        retryTemplate.registerListener(consumerRetryListener);// This listener takes care of listening to the activity and logs the necessary events.
        return retryTemplate;
    }

    /**
     * SimpleRetryPolicy sets the number of times the retry will happen.
     * @return
     */
    public SimpleRetryPolicy getRetryPolicy(){
        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy();
        simpleRetryPolicy.setMaxAttempts(maxRetries);
        return simpleRetryPolicy;
    }

    /**
     * FixedBackOffPolicy sets the interval between the retry.
     * @return
     */
    public FixedBackOffPolicy getBackOffPolicy() {
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(initialBackoffInterval);
        return backOffPolicy;
    }
}

package com.learnkafka.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.listener.RetryListenerSupport;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ConsumerRetryListener extends RetryListenerSupport {

    @Value("${spring.kafka.retry.generate-alert-retry-threshold}")
    private Integer maxRetries;

    @Override
    public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
        log.info("Retry context opened");
        return true;
    }

    @Override
    public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        if (context.getRetryCount() == Integer.valueOf(maxRetries)) {
            log.error("Retry Threshold reached");
        }
    }
    @Override
    public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
        log.info("Retry in onError");

    }

}

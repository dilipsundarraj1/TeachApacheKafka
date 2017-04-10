package com.learnbootkafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class ProducerKafka {
	
	 @Autowired
	    private KafkaTemplate<String, String> kafkaTemplate;
	 
	 @Autowired
	 Environment env;
	 
	 public void sendMessage(String message){
		  ListenableFuture<SendResult<String, String>> future = kafkaTemplate
	                .send(env.getProperty("kafka.topic"),message,message);
		  
		  future.addCallback(
	                new ListenableFutureCallback<SendResult<String,String>>() {

	                    @Override
	                    public void onFailure(Throwable ex) {
	                    	System.out.println("Inside Exception");
	           
	                    }

						@Override
						public void onSuccess(SendResult<String, String> result) {
							// TODO Auto-generated method stub
	                    	System.out.println("Inside Success");

						}
	                });

	 }

}

package com.learncamel.kafka;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelKafkaClient {
	
	public static void main(String[] args) throws Exception {
        CamelContext camelContext = new DefaultCamelContext();
        
        try {
			camelContext.addRoutes(new RouteBuilder() {
			    public void configure() {
			       // log.info("About to start route: Kafka Server -> Log ");
			    	
			        from("kafka:my-topic?brokers=localhost:9092"
			        		 + "&consumersCount=1"
			                + "&seekTo=beginning"
			                + "&groupId=group1")
			                .routeId("FromKafka")
			            .log("${body}");
			    }
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        camelContext.start();

        // Run fror 5 mins
        Thread.sleep(5 * 60 * 1000);

        camelContext.stop();

	}

}

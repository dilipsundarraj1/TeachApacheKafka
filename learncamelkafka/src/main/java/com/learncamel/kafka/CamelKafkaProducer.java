package com.learncamel.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaComponent;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.camel.impl.DefaultCamelContext;

public class CamelKafkaProducer {
	
	public static void main(String[] args) throws Exception {
		
        final CamelContext context = new DefaultCamelContext();
        
        try {
			context.addRoutes(new RouteBuilder() {
			    public void configure() {

			    	KafkaComponent kafka = new KafkaComponent();
	                kafka.setBrokers("localhost:9092");
	                context.addComponent("kafka", kafka);
	                
			        from("direct:pushtoTopic").routeId("DirectToKafka")
			            .to("kafka:my-topic").log("${headers}");

			    }

			});
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        ProducerTemplate producerTemplate = context.createProducerTemplate();
		 context.start();

	        Map<String, Object> headers = new HashMap<String, Object>();

	        headers.put(KafkaConstants.PARTITION_KEY, 0);
	        headers.put(KafkaConstants.KEY, "1");
	        for(int i=0;i<=5;i++){
	        	producerTemplate.sendBodyAndHeaders("direct:pushtoTopic"," Hi Hello " + i, headers);
	        }
	        
	        
	        Thread.sleep(5 * 60 * 1000);

	        context.stop();
        
	}

}

package com.qsi.kafka.camel.consumer.kafkaqsiconsumer;

import java.sql.Timestamp;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Service;

@Service
public class MessageProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        System.out.println("Sleeping");
        String message = exchange.getIn().getBody(String.class).toString();
        String topic = exchange.getIn().getHeader("kafka.TOPIC").toString();
        System.out.println("Message received: " + message + " from " + topic + " at " + new Timestamp(System.currentTimeMillis()).toString());
        Thread.sleep(100, 0); 
        
    }
    
}

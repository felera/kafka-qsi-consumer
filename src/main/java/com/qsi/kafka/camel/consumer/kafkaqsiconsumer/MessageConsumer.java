package com.qsi.kafka.camel.consumer.kafkaqsiconsumer;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer  extends RouteBuilder {


    @Value("${qsi.test.kafka.topics}")

	private String topicsList;

    @Value("${kafka.brokers.beta}")
	private String kafkaBrokers;

    @Value("${qsi.test.kafka.groupid}")
	private String groupId;

    @Value("${qsi.test.kafka.session.timeout.ms}")
	private String sessionTimeOut;

    @Value("${qsi.test.kafka.max.poll.interval.ms}")
	private String maxPollIntervalMs;
	
    @Value("${qsi.test.kafka.max.poll.records}")
	private String maxPollRecords;

	@Autowired
	private MessageProcessor messageProcessor;


    @Override
	public void configure() throws Exception {

		 String kafkaEndPoint = String.format(
			"kafka:%s?brokers=%s&groupId=%s&sessionTimeoutMs=%s&maxPollIntervalMs=%s&maxPollRecords=%s",	
			topicsList, kafkaBrokers, groupId, sessionTimeOut, maxPollIntervalMs, maxPollRecords);
		 	

//		String kafkaEndPoint = "kafka://first-topic?brokers=localhost:9092&groupId=qsi-consumer-group-1&maxPollIntervalMs=4000&maxPollRecords=100&sessionTimeoutMs=20000";

//		String kafkaEndPoint = "kafka://localhost:9092?topic=test";

		System.out.println("Trying to Connect to:" + kafkaEndPoint);
		

		from(kafkaEndPoint)
		.routeId("qsi-route")
		.process(messageProcessor);
		//.filter(simple("${header.sendMessage}"))
		//.marshal().json(JsonLibrary.Jackson)
		//.log(LoggingLevel.INFO, "Processing Message");

	}
}

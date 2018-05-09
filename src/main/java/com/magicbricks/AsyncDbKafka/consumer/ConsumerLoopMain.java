package com.magicbricks.AsyncDbKafka.consumer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ConsumerLoopMain {
	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("spring-config.xml");
		KafkaConsumerLoopClient kafkaConsumerLoopClient = (KafkaConsumerLoopClient) context
				.getBean("kafkaConsumerLoopClient");
		kafkaConsumerLoopClient.consumeView();
	}
}

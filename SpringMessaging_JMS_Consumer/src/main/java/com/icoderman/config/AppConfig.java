package com.icoderman.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@Configuration
@ComponentScan("com.icoderman.app")
public class AppConfig {

	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL("tcp://127.0.0.1:61616");
		return factory;
	}

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("MyFirstQueue");
	}

	@Bean
	public MessageConverter messageConverter() {
		MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
		messageConverter.setTypeIdPropertyName("id");
		return messageConverter;
	}

	@Bean
	public JmsOperations jmsTemplate(ConnectionFactory connectionFactory, Queue queue, MessageConverter messageConverter) {
		JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
		jmsTemplate.setDefaultDestination(queue);
		jmsTemplate.setMessageConverter(messageConverter);
		return jmsTemplate;
	}

}

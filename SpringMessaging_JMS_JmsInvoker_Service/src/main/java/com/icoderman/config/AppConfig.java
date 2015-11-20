package com.icoderman.config;


import com.icoderman.app.service.MyRemoteService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.remoting.JmsInvokerServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@Configuration
@ComponentScan("com.icoderman.app")
public class AppConfig {

	@Bean
	public Queue queue() {
		return new ActiveMQQueue("JmsInvokerQueue");
	}

	@Bean
	public ConnectionFactory connectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL("tcp://127.0.0.1:61616");
		return factory;
	}

	@Bean
	public JmsInvokerServiceExporter jmsInvokerServiceExporter(MyRemoteService myRemoteService) {
		JmsInvokerServiceExporter jmsInvoker = new JmsInvokerServiceExporter();
		jmsInvoker.setService(myRemoteService);
		jmsInvoker.setServiceInterface(MyRemoteService.class);
		return jmsInvoker;
	}

	@Bean
	public SimpleMessageListenerContainer simpleMessageListenerContainer(MyRemoteService myRemoteService) {
		SimpleMessageListenerContainer listenerContainer = new SimpleMessageListenerContainer();
		listenerContainer.setConnectionFactory(connectionFactory());
		listenerContainer.setDestination(queue());
		listenerContainer.setConcurrentConsumers(3);
		listenerContainer.setMessageListener(jmsInvokerServiceExporter(myRemoteService));
		return listenerContainer;
	}

}

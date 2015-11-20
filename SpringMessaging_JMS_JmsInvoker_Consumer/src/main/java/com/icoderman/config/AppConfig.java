package com.icoderman.config;


import com.icoderman.app.service.MyRemoteService;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.remoting.JmsInvokerProxyFactoryBean;
import org.springframework.jms.remoting.JmsInvokerServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;

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
	public JmsInvokerProxyFactoryBean myRemoteService() {
		JmsInvokerProxyFactoryBean factory = new JmsInvokerProxyFactoryBean();
		factory.setConnectionFactory(connectionFactory());
		factory.setQueueName("JmsInvokerQueue");
		factory.setServiceInterface(MyRemoteService.class);
		return factory;
	}

}

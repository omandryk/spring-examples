package com.icoderman.app.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyRemoteServiceImpl implements MyRemoteService {

	private AmqpTemplate rabbit;

	@Autowired
	public MyRemoteServiceImpl(AmqpTemplate rabbit) {
		this.rabbit = rabbit;
	}

	@Override
	public void sayHello(String name) {
		rabbit.convertAndSend("myExchange", "myRoutingKey", "Hello "+ name +" I'm RabbitMQ message");
	}
}

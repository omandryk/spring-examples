package com.icoderman.app.service;

import com.icoderman.app.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

@Service
public class AlertServiceImpl implements AlertService {

	private JmsOperations jmsOperations;

	@Autowired
	public AlertServiceImpl(JmsOperations jmsOperations) {
		this.jmsOperations = jmsOperations;
	}

	@Override
	public void alert(String message) {
		jmsOperations.send(
				"MyFirstQueue",
				new MessageCreator() {
					public Message createMessage(Session session) throws JMSException {
						return session.createObjectMessage(message);
					}
				}
		);
	}

	@Override
	public void alertPerson(Person person) {
		jmsOperations.convertAndSend(person);
	}
}

package com.icoderman.app.service;

import com.icoderman.app.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.JmsUtils;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

@Service
public class AlertServiceImpl implements AlertService {

	private JmsOperations jmsOperations;

	@Autowired
	public AlertServiceImpl(JmsOperations jmsOperations) {
		this.jmsOperations = jmsOperations;
	}

	@Override
	public String receiveAlert() {
		try {
			ObjectMessage receivedMessage = (ObjectMessage) jmsOperations.receive();
			return (String) receivedMessage.getObject();
		} catch (JMSException jmsException) {
			throw JmsUtils.convertJmsAccessException(jmsException);
		}
	}

	@Override
	public Person receiveAlertPerson() {
		return (Person)jmsOperations.receiveAndConvert();
	}
}

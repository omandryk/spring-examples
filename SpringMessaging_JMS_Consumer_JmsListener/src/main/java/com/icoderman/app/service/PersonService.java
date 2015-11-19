package com.icoderman.app.service;

import com.icoderman.app.model.Person;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

	@JmsListener(destination = "MyFirstQueue")
	public void processOrder(Person person) {
		System.out.println("I'm going to receive message...");
		System.out.println(person);
	}
}

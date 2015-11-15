package com.icoderman.app;

import com.icoderman.app.dao.PersonRepository;
import com.icoderman.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		PersonRepository personRepository = context.getBean(PersonRepository.class);

		System.out.println(personRepository.findOne(57L));
		System.out.println(personRepository.findOne(57L));
		System.out.println(personRepository.findOne(57L));
		personRepository.deleteOn(57L);
	}
}

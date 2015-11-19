package com.icoderman.app;

import com.icoderman.app.model.Person;
import com.icoderman.app.service.AlertService;
import com.icoderman.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		AlertService alertService = context.getBean(AlertService.class);
		//alertService.alert("icoderman");
		System.out.println("I'm going to send message...");
		alertService.alertPerson(new Person(123L, "icoderman", 29));
	}
}
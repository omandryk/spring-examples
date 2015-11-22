package com.icoderman.app;

import com.icoderman.app.service.MyRemoteService;
import com.icoderman.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		MyRemoteService myRemoteService = context.getBean(MyRemoteService.class);
		System.out.println(myRemoteService.getHello());
	}
}

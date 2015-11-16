package com.icoderman.app.service;

import org.springframework.stereotype.Service;

@Service
public class MyRemoteServiceImpl implements MyRemoteService {

	@Override
	public String sayHello(String name) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

		}
		return "Hello "+ name + ", I'm Hessian Service!";
	}
}

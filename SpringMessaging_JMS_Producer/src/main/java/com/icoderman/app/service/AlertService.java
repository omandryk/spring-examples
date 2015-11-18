package com.icoderman.app.service;

import com.icoderman.app.model.Person;

public interface AlertService {
	void alert(String message);
	void alertPerson(Person person);
}

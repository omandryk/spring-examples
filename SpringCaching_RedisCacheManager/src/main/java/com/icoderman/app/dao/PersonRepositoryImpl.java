package com.icoderman.app.dao;

import com.icoderman.app.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

	@Override
	public Person findOne(Long id) {
		slowQuery(2000);
		Person person = new Person();
		person.setId(id);
		person.setName("John");
		return person;
	}

	@Override
	public void deleteOn(Long id) {
		System.out.println("Person deleted");
	}

	private void slowQuery(int seconds) {
		System.out.println("slowQuery is running...");
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}

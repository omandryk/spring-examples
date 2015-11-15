package com.icoderman.app.dao;

import com.icoderman.app.model.Person;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRepositoryImpl implements PersonRepository {

	public Person findOne(Long id) {
		slowQuery(2000L);
		return new Person(123L, "Oleksandr");
	}

	private void slowQuery(long seconds) {
		System.out.println("slowQuery is running...");
		try {
			Thread.sleep(seconds);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}
}

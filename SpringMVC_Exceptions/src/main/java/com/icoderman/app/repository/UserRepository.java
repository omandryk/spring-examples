package com.icoderman.app.repository;

import com.icoderman.app.exceptions.DuplicateUserException;
import com.icoderman.app.exceptions.IncorrectUserException;
import com.icoderman.app.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	public void save() {
		throw new DuplicateUserException();
	}

	public void update() {
		throw new IncorrectUserException();
	}

	public User findOne(long userId) {
		return null;
	}
}

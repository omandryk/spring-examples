package com.icoderman.app.dao;

import com.icoderman.app.model.User;
import java.util.List;

public interface UserRepository {

	long count();

	User save(User user);

	User findOne(long id);

	User findByUsername(String username);

	List<User> findAll();
}

package com.icoderman.app.dao;

import com.icoderman.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

}

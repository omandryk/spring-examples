package com.icoderman.app.dao;

import com.icoderman.app.model.Person;
import org.springframework.cache.annotation.Cacheable;

public interface PersonRepository {

	@Cacheable("PersonCache")
	Person findOne(Long id);

}

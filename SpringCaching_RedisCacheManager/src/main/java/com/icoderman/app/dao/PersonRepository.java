package com.icoderman.app.dao;

import com.icoderman.app.model.Person;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

public interface PersonRepository {

	@Cacheable("persons")
	Person findOne(Long id);

	@CacheEvict("persons")
	void deleteOn(Long id);

}

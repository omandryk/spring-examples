package com.icoderman.app.dao.hibernate;

import com.icoderman.app.dao.UserRepository;
import com.icoderman.app.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class HibernateJPAUserRepository implements UserRepository {

	@PersistenceContext
	private EntityManager entityManager;


	public long count() {
		return findAll().size();
	}

	public User save(User user) {
		entityManager.persist(user);
		return user;
	}

	public User findOne(long id) {
		return entityManager.find(User.class, id);
	}

	public User findByUsername(String username) {
		return (User) entityManager.createQuery("select s from users s where s.username=?").setParameter(1, username).getSingleResult();
	}

	public List<User> findAll() {
		return (List<User>) entityManager.createQuery("select s from users s").getResultList();
	}
}

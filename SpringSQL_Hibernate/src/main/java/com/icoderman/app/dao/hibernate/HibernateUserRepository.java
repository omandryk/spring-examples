package com.icoderman.app.dao.hibernate;

import com.icoderman.app.dao.UserRepository;
import com.icoderman.app.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;

@Repository
public class HibernateUserRepository implements UserRepository {

	private SessionFactory sessionFactory;

	@Inject
	public HibernateUserRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	public long count() {
		return findAll().size();
	}

	public User save(User user) {
		Serializable id = currentSession().save(user);
		return new User((Long) id,
				user.getUsername(),
				user.getPassword(),
				user.getFullName(),
				user.getEmail());
	}

	public User findOne(long id) {
		return (User) currentSession().get(User.class, id);
	}

	public User findByUsername(String username) {
		return (User) currentSession()
				.createCriteria(User.class)
				.add(Restrictions.eq("username", username))
				.list().get(0);
	}

	public List<User> findAll() {
		return (List<User>) currentSession()
				.createCriteria(User.class).list();
	}

}

package com.icoderman.app.dao.jdbc;

import com.icoderman.app.dao.UserRepository;
import com.icoderman.app.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcUserRepository implements UserRepository {

	private static final String INSERT_USER = "insert into users (username, password, fullname, email) values (?, ?, ?, ?)";
	private static final String SELECT_USER_BY_ID = "select id, username, password, fullname, email from users where id=?";
	private static final String SELECT_USER_BY_USERNAME = "select id, username, password, fullname, email from users where username=?";
	private static final String SELECT_ALL_USERS = "select id, username, password, fullname, email from users order by id";
	private static final String UPDATE_USER = "update users set username=?, password=?, fullname=?, email=? where id=?";

	private JdbcTemplate jdbcTemplate;


	public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public long count() {
		return jdbcTemplate.queryForObject("select count(id) from users", Long.class);
	}

	public User save(User user) {
		Long id = user.getId();
		if (id == null) {
			long userId = insertUserAndReturnId(user);
			return new User(userId, user.getUsername(), user.getPassword(), user.getFullName(), user.getEmail());
		} else {
			jdbcTemplate.update(UPDATE_USER,
					user.getUsername(),
					user.getPassword(),
					user.getFullName(),
					user.getEmail(),
					id);
		}
		return user;
	}

	public User findOne(long id) {
		return jdbcTemplate.queryForObject(SELECT_USER_BY_ID, new UserRowMapper(), id);
	}

	public User findByUsername(String username) {
		return jdbcTemplate.queryForObject(SELECT_USER_BY_USERNAME, new UserRowMapper(), username);
	}

	public List<User> findAll() {
		return jdbcTemplate.query(SELECT_ALL_USERS, new UserRowMapper());
	}

	private long insertUserAndReturnId(User spitter) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate).withTableName("users");
		jdbcInsert.setGeneratedKeyName("id");
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("username", spitter.getUsername());
		args.put("password", spitter.getPassword());
		args.put("fullname", spitter.getFullName());
		args.put("email", spitter.getEmail());
		long userId = jdbcInsert.executeAndReturnKey(args).longValue();
		return userId;
	}

	private static final class UserRowMapper implements RowMapper<User> {
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			long id = rs.getLong("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String fullName = rs.getString("fullname");
			String email = rs.getString("email");
			return new User(id, username, password, fullName, email);
		}
	}
}

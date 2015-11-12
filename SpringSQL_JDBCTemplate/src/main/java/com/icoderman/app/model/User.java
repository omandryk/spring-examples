package com.icoderman.app.model;

public class User {
	private Long id;
	private String username;
	private String password;
	private String fullName;
	private String email;

	public User(Long id, String username, String password, String fullName, String email) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getFullName() {
		return fullName;
	}

	public String getEmail() {
		return email;
	}
}

package com.icoderman.app.model;

public class User {
	private String username;
	private String firstName;
	private String lastName;
	private Integer age;

	public User() {
	}

	public User(String username, String firstName, String lastName, Integer age) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}

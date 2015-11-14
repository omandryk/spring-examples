package com.icoderman.app.exceptions;

public class PersonNotFoundException extends RuntimeException {
	private Long personId;

	public PersonNotFoundException(Long personId) {
		this.personId = this.personId;
	}

	public long getPersonId() {
		return personId;
	}
}

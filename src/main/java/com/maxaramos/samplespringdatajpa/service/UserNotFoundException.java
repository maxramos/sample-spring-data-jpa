package com.maxaramos.samplespringdatajpa.service;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -6899861199152405762L;

	public UserNotFoundException(String message) {
		super(message);
	}

}

package com.maxaramos.samplespringdatajpa.service;

public class AuthorityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 8028528928213817245L;

	public AuthorityNotFoundException(String message) {
		super(message);
	}

}

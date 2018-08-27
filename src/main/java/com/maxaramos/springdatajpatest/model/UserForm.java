package com.maxaramos.springdatajpatest.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserForm {

	@NotBlank
	@Size(min = 5, max = 25)
	private String username;

	@NotBlank
	@Size(min = 4, max = 16)
	private String password;

	@NotBlank
	@Size(min = 4, max = 16)
	private String confirmPassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public String toString() {
		return String.format("RegisterForm [username=%s, password=%s, confirmPassword=%s]", username, password, confirmPassword);
	}

}

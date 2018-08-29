package com.maxaramos.springdatajpatest.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserForm {

	@NotBlank
	@Size(min = 1, max = 20)
	private String username;

	@NotBlank
	@Size(min = 4, max = 16)
	private String password;

	@NotBlank
	@Size(min = 4, max = 16)
	private String confirmPassword;

	@NotBlank
	@Size(min = 5, max = 30)
	@Pattern(regexp = "^.+@.+\\..+$")
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("UserForm [username=%s, password=%s, confirmPassword=%s, email=%s]", username, password, confirmPassword, email);
	}

}

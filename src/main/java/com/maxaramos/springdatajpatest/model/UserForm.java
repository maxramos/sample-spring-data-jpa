package com.maxaramos.springdatajpatest.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

public class UserForm {

	public interface SaveConstraintGroup {}

	public interface ChangePasswordConstraintGroup {}

	@NotBlank(groups = { Default.class, SaveConstraintGroup.class })
	@Size(min = 1, max = 20, groups = { Default.class, SaveConstraintGroup.class })
	private String username;

	@NotBlank(groups = { Default.class, ChangePasswordConstraintGroup.class })
	@Size(min = 4, max = 16, groups = { Default.class, ChangePasswordConstraintGroup.class })
	private String password;

	@NotBlank(groups = { Default.class, ChangePasswordConstraintGroup.class })
	@Size(min = 4, max = 16, groups = { Default.class, ChangePasswordConstraintGroup.class })
	private String confirmPassword;

	@NotBlank(groups = { Default.class, SaveConstraintGroup.class })
	@Size(min = 5, max = 30, groups = { Default.class, SaveConstraintGroup.class })
	@Email(groups = { Default.class, SaveConstraintGroup.class })
	private String email;

	@NotBlank(groups = { Default.class, SaveConstraintGroup.class })
	@Size(min = 1, max = 20, groups = { Default.class, SaveConstraintGroup.class })
	private String firstName;

	@NotBlank(groups = { Default.class, SaveConstraintGroup.class })
	@Size(min = 1, max = 20, groups = { Default.class, SaveConstraintGroup.class })
	private String lastName;

	public static UserForm fromUser(User user) {
		UserForm userForm = new UserForm();
		userForm.username = user.getUsername();
		userForm.password = user.getPassword();
		userForm.confirmPassword = user.getPassword();
		userForm.email = user.getEmail();
		userForm.firstName = user.getFirstName();
		userForm.lastName = user.getLastName();
		return userForm;
	}

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

	@Override
	public String toString() {
		return String.format("UserForm [username=%s, password=%s, confirmPassword=%s, email=%s, firstName=%s, lastName=%s]", username, password, confirmPassword, email, firstName,
				lastName);
	}

}

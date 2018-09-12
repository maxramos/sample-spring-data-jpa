package com.maxaramos.springdatajpatest.model;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

public class UserForm {

	public interface SaveConstraintGroup {}

	public interface ChangePasswordConstraintGroup {}

	@Size(min = 1, max = 20, groups = { Default.class, SaveConstraintGroup.class })
	private String username;

	@Size(min = 4, max = 16, groups = { Default.class, ChangePasswordConstraintGroup.class })
	private String password;

	@Size(min = 4, max = 16, groups = { Default.class, ChangePasswordConstraintGroup.class })
	private String confirmPassword;

	@Size(min = 5, max = 30, groups = { Default.class, SaveConstraintGroup.class })
	@Email(groups = { Default.class, SaveConstraintGroup.class })
	private String email;

	@Size(min = 1, max = 20, groups = { Default.class, SaveConstraintGroup.class })
	private String firstName;

	@Size(min = 1, max = 20, groups = { Default.class, SaveConstraintGroup.class })
	private String lastName;

	@Positive(groups = { Default.class, SaveConstraintGroup.class })
	private Integer age;

	@Past(groups = { Default.class, SaveConstraintGroup.class })
	private LocalDate birthday;

	@NotNull(groups = { Default.class, SaveConstraintGroup.class })
	private GenderType gender;

	private AddressForm address = new AddressForm();

	public static UserForm fromUser(User user) {
		UserForm userForm = new UserForm();
		userForm.username = user.getUsername();
		userForm.password = user.getPassword();
		userForm.confirmPassword = user.getPassword();
		userForm.email = user.getEmail();
		userForm.firstName = user.getFirstName();
		userForm.lastName = user.getLastName();
		userForm.address = AddressForm.fromAddress(user.getAddress());
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	public AddressForm getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return String.format("UserForm [username=%s, password=%s, confirmPassword=%s, email=%s, firstName=%s, lastName=%s, age=%s, birthday=%s, gender=%s, address=%s]", username,
				password, confirmPassword, email, firstName, lastName, age, birthday, gender, address);
	}

}

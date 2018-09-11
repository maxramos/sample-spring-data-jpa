package com.maxaramos.springdatajpatest.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	public static final String LOGGED_IN_USER_ATTR = "loggedInUser";

	private static final long serialVersionUID = 2994511031102491790L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_authorities",
			joinColumns = @JoinColumn(name = "user_id", table = "users", referencedColumnName = "id"),
			inverseJoinColumns= @JoinColumn(name = "authority_id", table = "authorities", referencedColumnName = "id"))
	private Set<Authority> authorities;

	@Column(name = "enabled")
	private Boolean enabled;

	@Column(name = "email")
	private String email;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "age")
	private Integer age;

	@Column(name = "birthday")
	private LocalDate birthday;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private GenderType gender;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id")
	private Address address;

	public User() {
		super();
	}

	public User(String username) {
		this.username = username;
		authorities = new HashSet<>();
		enabled = true;
	}

	public void addAuthority(Authority authority) {
		authorities.add(authority);
	}

	public Long getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	public String getRole() {
		Optional<Authority> authority = authorities.stream().findAny();
		String role = null;

		if (authority.isPresent()) {
			role = authority.get().getAuthority().split("_")[1].toLowerCase();
		}

		return role;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, username=%s, password=%s, authorities=%s, enabled=%s, email=%s, firstName=%s, lastName=%s, age=%s, birthday=%s, gender=%s, address=%s]",
				id, username, password, authorities, enabled, email, firstName, lastName, age, birthday, gender, address);
	}

}

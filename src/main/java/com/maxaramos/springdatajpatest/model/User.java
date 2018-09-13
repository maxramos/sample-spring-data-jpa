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
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.userdetails.UserDetails;

import com.maxaramos.springdatajpatest.validation.ConstraintGroups.ChangePassword;
import com.maxaramos.springdatajpatest.validation.ConstraintGroups.Save;

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
	@Size(min = 1, max = 20, groups = { Default.class, Save.class })
	private String username;

	@Column(name = "password")
	private String password;

	@Transient
	@Size(min = 4, max = 16, groups = { Default.class, ChangePassword.class })
	private String rawPassword;

	@Transient
	@Size(min = 4, max = 16, groups = { Default.class, ChangePassword.class })
	private String confirmRawPassword;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_authorities",
			joinColumns = @JoinColumn(name = "user_id", table = "users", referencedColumnName = "id"),
			inverseJoinColumns= @JoinColumn(name = "authority_id", table = "authorities", referencedColumnName = "id"))
	private Set<Authority> authorities = new HashSet<>();

	@Column(name = "enabled")
	private Boolean enabled = true;

	@Column(name = "email")
	@Size(min = 5, max = 30, groups = { Default.class, Save.class })
	@Email(groups = { Default.class, Save.class })
	private String email;

	@Column(name = "first_name")
	@Size(min = 1, max = 20, groups = { Default.class, Save.class })
	private String firstName;

	@Column(name = "last_name")
	@Size(min = 1, max = 20, groups = { Default.class, Save.class })
	private String lastName;

	@Column(name = "age")
	@NotNull(groups = { Default.class, Save.class })
	@Positive(groups = { Default.class, Save.class })
	private Integer age;

	@Column(name = "birthday")
	@NotNull(groups = { Default.class, Save.class })
	@Past(groups = { Default.class, Save.class })
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate birthday;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	@NotNull(groups = { Default.class, Save.class })
	private GenderType gender;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id")
	@Valid
	private Address address = new Address();

	public User() {
		super();
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

	public String getRawPassword() {
		return rawPassword;
	}

	public void setRawPassword(String rawPassword) {
		this.rawPassword = rawPassword;
	}

	public String getConfirmRawPassword() {
		return confirmRawPassword;
	}

	public void setConfirmRawPassword(String confirmRawPassword) {
		this.confirmRawPassword = confirmRawPassword;
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

	public String getFullName() {
		return firstName + " " + lastName;
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
		return String.format(
				"User [id=%s, username=%s, password=%s, rawPassword=%s, confirmRawPassword=%s, authorities=%s, enabled=%s, email=%s, firstName=%s, lastName=%s, age=%s, birthday=%s, gender=%s, address=%s]",
				id, username, password, rawPassword, confirmRawPassword, authorities, enabled, email, firstName, lastName, age, birthday, gender, address);
	}

}

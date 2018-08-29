package com.maxaramos.springdatajpatest.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {

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

	public Authority getRole() {
		return authorities.stream().findAny().orElse(null);
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

	@Override
	public String toString() {
		return String.format("User [id=%s, username=%s, password=%s, authorities=%s, enabled=%s, email=%s]", id, username, password, authorities, enabled, email);
	}

}

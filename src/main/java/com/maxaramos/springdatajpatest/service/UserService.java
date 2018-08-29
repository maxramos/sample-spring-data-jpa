package com.maxaramos.springdatajpatest.service;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.maxaramos.springdatajpatest.dao.AuthorityDao;
import com.maxaramos.springdatajpatest.dao.UserDao;
import com.maxaramos.springdatajpatest.model.Authority;
import com.maxaramos.springdatajpatest.model.User;
import com.maxaramos.springdatajpatest.model.UserForm;

@Service
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	private Logger log;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthorityDao authorityDao;

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Username: " + username);
		}

		log.debug(user.toString());
		return user;
	}

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public void save(UserForm userForm) {
		Authority authority = authorityDao.findByAuthority("ROLE_USER");
		User user = new User(userForm.getUsername());
		user.setPassword(passwordEncoder.encode(userForm.getPassword()));
		user.addAuthority(authority);
		user.setEmail(userForm.getEmail());
		userDao.save(user);
	}

}

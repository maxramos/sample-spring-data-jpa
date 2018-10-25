package com.maxaramos.springdatajpatest.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maxaramos.springdatajpatest.dao.AuthorityDao;
import com.maxaramos.springdatajpatest.dao.UserDao;
import com.maxaramos.springdatajpatest.model.Address;
import com.maxaramos.springdatajpatest.model.Authority;
import com.maxaramos.springdatajpatest.model.User;

@Service
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	private Logger log;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthorityDao authorityDao;

	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username: " + username));
		log.debug(user.toString());
		return user;
	}

	public List<User> findAll() {
		return userDao.findAll();
	}

	public List<User> findAllBySupervisor(User supervisor) {
		User savedSupervisor = userDao.findById(supervisor.getId()).orElseThrow(() -> new UserNotFoundException("Id: " + supervisor.getId()));
		List<User> users = new ArrayList<>();

		if ("admin".equals(savedSupervisor.getRole())) {
			users.addAll(userDao.findAll());
		}else if ("depthead".equals(savedSupervisor.getRole())) {
			users.add(savedSupervisor);

			for (User teamSupervisor : savedSupervisor.getSupervisees()) {
				users.add(teamSupervisor);
				users.addAll(teamSupervisor.getSupervisees());
			}
		} else if ("supervisor".equals(savedSupervisor.getRole())) {
			users.add(savedSupervisor);
			users.addAll(savedSupervisor.getSupervisees());
		}

		return users;
	}

	public User findById(Long id) {
		return userDao.findById(id).orElse(null);
	}

	public User findByUsername(String username) {
		return userDao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username: " + username));
	}

	public User register(User user) {
		user.setPassword(passwordEncoder.encode(user.getRawPassword()));
		user.addAuthority(authorityDao.findByAuthority(Authority.ROLE_EMPLOYEE).orElseThrow(() -> new AuthorityNotFoundException("Authority: " + Authority.ROLE_EMPLOYEE)));
		return userDao.save(user);
	}

	public User save(Long id, User user) {
		User savedUser = userDao.findById(id).orElseThrow(() -> new UserNotFoundException("Id: " + id));
		savedUser.setUsername(user.getUsername());
		savedUser.setEmail(user.getEmail());
		savedUser.setFirstName(user.getFirstName());
		savedUser.setLastName(user.getLastName());
		savedUser.setAge(user.getAge());
		savedUser.setBirthday(user.getBirthday());
		savedUser.setGender(user.getGender());

		Address address = user.getAddress();
		Address savedAddress = savedUser.getAddress();
		savedAddress.setAddress1(address.getAddress1());
		savedAddress.setAddress2(address.getAddress2());
		savedAddress.setCity(address.getCity());
		savedAddress.setState(address.getState());
		savedAddress.setCountry(address.getCountry());
		savedAddress.setZipCode(address.getZipCode());

		return userDao.save(savedUser);
	}

	public User changePassword(Long id, User user) {
		User savedUser = userDao.findById(id).orElseThrow(() -> new UserNotFoundException("Id: " + id));
		savedUser.setPassword(passwordEncoder.encode(user.getRawPassword()));
		return userDao.save(savedUser);
	}

	public void deleteById(Long id) {
		userDao.deleteById(id);
	}

	public User enableById(Long id) {
		User user = userDao.findById(id).orElseThrow(() -> new UserNotFoundException("Id: " + id));
		user.setEnabled(true);
		return userDao.save(user);
	}

	public User disableById(Long id) {
		User user = userDao.findById(id).orElseThrow(() -> new UserNotFoundException("Id: " + id));
		user.setEnabled(false);
		return userDao.save(user);
	}

}

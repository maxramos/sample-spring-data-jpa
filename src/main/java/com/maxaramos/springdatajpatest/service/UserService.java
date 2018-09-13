package com.maxaramos.springdatajpatest.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.maxaramos.springdatajpatest.dao.AuthorityDao;
import com.maxaramos.springdatajpatest.dao.UserDao;
import com.maxaramos.springdatajpatest.model.Address;
import com.maxaramos.springdatajpatest.model.AddressForm;
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
		User user = userDao.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username: " + username));
		log.debug(user.toString());
		return user;
	}

	public List<User> findAll() {
		return userDao.findAll();
	}

	public User findByUsername(String username) {
		return userDao.findByUsername(username).orElse(null);
	}

	public User register(UserForm userForm) {
		AddressForm addressForm = userForm.getAddress();
		Address address = new Address();
		address.setAddress1(addressForm.getAddress1());
		address.setAddress2(addressForm.getAddress2());
		address.setCity(addressForm.getCity());
		address.setState(addressForm.getState());
		address.setCountry(addressForm.getCountry());
		address.setZipCode(addressForm.getZipCode());

		User user = new User(userForm.getUsername());
		user.setPassword(passwordEncoder.encode(userForm.getPassword()));
		user.addAuthority(authorityDao.findByAuthority("ROLE_USER").orElse(null));
		user.setEmail(userForm.getEmail());
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setAge(userForm.getAge());
		user.setBirthday(userForm.getBirthday());
		user.setGender(userForm.getGender());
		user.setAddress(address);
		return userDao.save(user);
	}

	public User save(Long id, UserForm userForm) {
		Optional<User> result = userDao.findById(id);

		if (!result.isPresent()) {
			throw new UserNotFoundException();
		}

		User user = result.get();
		user.setUsername(userForm.getUsername());
		user.setEmail(userForm.getEmail());
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setAge(userForm.getAge());
		user.setBirthday(userForm.getBirthday());
		user.setGender(userForm.getGender());

		AddressForm addressForm = userForm.getAddress();
		Address address = user.getAddress();
		address.setAddress1(addressForm.getAddress1());
		address.setAddress2(addressForm.getAddress2());
		address.setCity(addressForm.getCity());
		address.setState(addressForm.getState());
		address.setCountry(addressForm.getCountry());
		address.setZipCode(addressForm.getZipCode());

		return userDao.save(user);
	}

	public User changePassword(Long id, UserForm userForm) {
		Optional<User> result = userDao.findById(id);

		if (!result.isPresent()) {
			throw new UserNotFoundException();
		}

		User user = result.get();
		user.setPassword(passwordEncoder.encode(userForm.getPassword()));
		return userDao.save(user);
	}

	public void deleteById(Long id) {
		userDao.deleteById(id);
	}

}

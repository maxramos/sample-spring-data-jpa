package com.maxaramos.springdatajpatest.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.RepositoryDefinition;

import com.maxaramos.springdatajpatest.model.User;

@RepositoryDefinition(
		domainClass = User.class,
		idClass = Long.class)
public interface UserDao {

	List<User> findAll();

	List<User> findAllBySupervisor(User supervisor);

	Optional<User> findById(Long id);

	Optional<User> findByUsername(String username);

	User save(User user);

	void deleteById(Long id);

}

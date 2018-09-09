package com.maxaramos.springdatajpatest.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.maxaramos.springdatajpatest.model.User;

public interface UserDao extends Repository<User, String> {

	List<User> findAll();

	Optional<User> findByUsername(String username);

	User save(User user);

}

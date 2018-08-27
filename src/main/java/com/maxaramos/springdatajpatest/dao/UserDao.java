package com.maxaramos.springdatajpatest.dao;

import org.springframework.data.repository.Repository;

import com.maxaramos.springdatajpatest.model.User;

public interface UserDao extends Repository<User, String> {

	User findByUsername(String username);

	void save(User user);

}

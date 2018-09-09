package com.maxaramos.springdatajpatest.dao;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.maxaramos.springdatajpatest.model.Authority;

public interface AuthorityDao extends Repository<Authority, String> {

	Optional<Authority> findByAuthority(String authority);

}

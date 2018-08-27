package com.maxaramos.springdatajpatest.dao;

import org.springframework.data.repository.Repository;

import com.maxaramos.springdatajpatest.model.Authority;

public interface AuthorityDao extends Repository<Authority, String> {

	Authority findByAuthority(String authority);

}

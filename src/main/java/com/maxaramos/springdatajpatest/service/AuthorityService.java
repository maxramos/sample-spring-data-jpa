package com.maxaramos.springdatajpatest.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxaramos.springdatajpatest.dao.AuthorityDao;
import com.maxaramos.springdatajpatest.model.Authority;

@Service
@Transactional
public class AuthorityService {

	@Autowired
	private AuthorityDao authorityDao;

	public Authority findByAuthority(String authority) {
		return authorityDao.findByAuthority(authority).orElseThrow(() -> new AuthorityNotFoundException("Authority: " + authority));
	}

}

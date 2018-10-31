package com.maxaramos.samplespringdatajpa.dao;

import java.util.Optional;

import org.springframework.data.repository.RepositoryDefinition;

import com.maxaramos.samplespringdatajpa.model.Authority;

@RepositoryDefinition(
		domainClass = Authority.class,
		idClass = Long.class)
public interface AuthorityDao {

	Optional<Authority> findByAuthority(String authority);

}

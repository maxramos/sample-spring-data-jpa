package com.maxaramos.springdatajpatest.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.RepositoryDefinition;

import com.maxaramos.springdatajpatest.model.Department;

@RepositoryDefinition(
		domainClass = Department.class,
		idClass = Long.class)
public interface DepartmentDao {

	List<Department> findAll();

	Optional<Department> findById(Long id);

}

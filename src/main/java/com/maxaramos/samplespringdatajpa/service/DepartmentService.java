package com.maxaramos.samplespringdatajpa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxaramos.samplespringdatajpa.dao.DepartmentDao;
import com.maxaramos.samplespringdatajpa.model.Department;

@Service
@Transactional
public class DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;

	public List<Department> findAll() {
		return departmentDao.findAll();
	}

	public Department findById(Long id) {
		return departmentDao.findById(id).orElse(null);
	}

}

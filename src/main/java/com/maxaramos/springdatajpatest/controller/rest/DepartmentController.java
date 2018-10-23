package com.maxaramos.springdatajpatest.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.maxaramos.springdatajpatest.jsonview.BasicView;
import com.maxaramos.springdatajpatest.jsonview.DepartmentView;
import com.maxaramos.springdatajpatest.model.Department;
import com.maxaramos.springdatajpatest.service.DepartmentService;

@RestController("/api/departments")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping
	@JsonView(BasicView.class)
	public List<Department> findAll() {
		return departmentService.findAll();
	}

	@GetMapping("/{id}")
	@JsonView(DepartmentView.class)
	public Department findById(Long id) {
		return departmentService.findById(id);
	}

}

package com.maxaramos.samplespringdatajpa.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.maxaramos.samplespringdatajpa.jsonview.BasicView;
import com.maxaramos.samplespringdatajpa.jsonview.DepartmentView;
import com.maxaramos.samplespringdatajpa.model.Department;
import com.maxaramos.samplespringdatajpa.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
public class DepartmentRestController {

	@Autowired
	private DepartmentService departmentService;

	@GetMapping
	@JsonView(BasicView.class)
	public List<Department> findAll() {
		return departmentService.findAll();
	}

	@GetMapping("/{id}")
	@JsonView(DepartmentView.class)
	public Department findById(@PathVariable("id") Long id) {
		return departmentService.findById(id);
	}

}

package com.maxaramos.samplespringdatajpa.controller.rest;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.maxaramos.samplespringdatajpa.jsonview.BasicView;
import com.maxaramos.samplespringdatajpa.jsonview.UserView;
import com.maxaramos.samplespringdatajpa.model.User;
import com.maxaramos.samplespringdatajpa.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

	@Autowired
	private UserService userService;

	@GetMapping
	@JsonView(BasicView.class)
	public List<User> findAll() {
		return userService.findAll();
	}

	@GetMapping("/{id}")
	@JsonView(UserView.class)
	public User findById(@PathVariable("id") Long id) {
		return userService.findById(id);
	}

	@PostMapping
	@JsonView(UserView.class)
	public User add(@RequestBody User user) {
		return userService.register(user);
	}

	@PutMapping
	@JsonView(UserView.class)
	public User update(@RequestBody User user, HttpSession session) {
		return userService.save(user.getId(), user);
	}

	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") Long id) {
		userService.deleteById(id);
		return "User: " + id + " deleted.";
	}

}

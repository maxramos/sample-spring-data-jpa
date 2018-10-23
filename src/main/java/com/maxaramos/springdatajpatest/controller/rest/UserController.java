package com.maxaramos.springdatajpatest.controller.rest;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.maxaramos.springdatajpatest.model.User;
import com.maxaramos.springdatajpatest.service.UserService;

@RestController("/api/users")
public class UserController {

	private UserService userService;

	@GetMapping
	public List<User> findAll() {
		return userService.findAll();
	}

	@GetMapping("/{id}")
	public User findById(Long id) {
		return userService.findById(id);
	}

	@PostMapping
	public User add(@RequestBody User user) {
		return userService.register(user);
	}

	@PutMapping
	public User update(@RequestBody User user, HttpSession session) {
		return userService.save(user.getId(), user);
	}

	@DeleteMapping("/{id}")
	public String deleteById(Long id) {
		userService.deleteById(id);
		return "User: " + id + " deleted.";
	}

}

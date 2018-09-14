package com.maxaramos.springdatajpatest.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.maxaramos.springdatajpatest.model.User;
import com.maxaramos.springdatajpatest.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private Logger log;

	@Autowired
	private UserService userService;

	@GetMapping("/users")
	public String users(Model model) {
		model.addAttribute("users", userService.findAll());
		return "/admin/users";
	}

	@PostMapping("/user/delete")
	public String deleteUser(@RequestParam("id") Long id) {
		userService.deleteById(id);
		return "redirect:/admin/users";
	}

	@PostMapping("/user/enable")
	public String enableUser(@RequestParam("id") Long id) {
		User updatedUser = userService.enableById(id);
		log.debug("Updated user: {}", updatedUser);
		return "redirect:/admin/users";
	}

	@PostMapping("/user/disable")
	public String disableUser(@RequestParam("id") Long id) {
		User updatedUser = userService.disableById(id);
		log.debug("Updated user: {}", updatedUser);
		return "redirect:/admin/users";
	}

}

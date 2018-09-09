package com.maxaramos.springdatajpatest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
	private UserService userService;

	@GetMapping("/profile")
	public String profile(Model model, Authentication authentication) {
		User user = userService.findByUsername(authentication.getName());
		model.addAttribute("user", user);
		return "/admin/profile";
	}

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

}

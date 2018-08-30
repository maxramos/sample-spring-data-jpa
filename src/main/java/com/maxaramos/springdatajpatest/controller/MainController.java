package com.maxaramos.springdatajpatest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.maxaramos.springdatajpatest.model.User;
import com.maxaramos.springdatajpatest.service.UserService;

@Controller
public class MainController {

	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index(Model model, Authentication authentication) {
		return "/index";
	}

	@GetMapping("/user/profile")
	public String profile(Model model, Authentication authentication) {
		User user = userService.findByUsername(authentication.getName());
		model.addAttribute("user", user);
		return "/user/profile";
	}

}

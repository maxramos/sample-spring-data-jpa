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
		User user = userService.findByUsername(authentication.getName());

		if (user != null) {
			model.addAttribute("username", user.getUsername());
			model.addAttribute("password", user.getPassword());
			model.addAttribute("role", user.getAuthorities().stream().findAny().orElse(null));
		}

		String authority = user.getAuthorities().stream().findFirst().get().getAuthority();
		return "ROLE_ADMIN".equals(authority) ? "/admin/index" : "/index";
	}

}

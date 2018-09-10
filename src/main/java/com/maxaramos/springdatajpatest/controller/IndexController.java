package com.maxaramos.springdatajpatest.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping({ "/", "/index" })
	public String index(Model model, Authentication authentication) {
		return "/index";
	}

}

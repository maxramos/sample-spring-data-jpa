package com.maxaramos.springdatajpatest.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maxaramos.springdatajpatest.model.GenderType;
import com.maxaramos.springdatajpatest.model.User;
import com.maxaramos.springdatajpatest.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

	@Autowired
	private Logger log;

	@Autowired
	private UserService userService;

	@ModelAttribute(name = "genders")
	public GenderType[] getGenders() {
		return GenderType.values();
	}

	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("user", new User());
		return "/registration/form";
	}

	@PostMapping("/register")
	public String register(@Validated User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.debug("Invalid user: {}", user);
			return "/registration/form";
		}

		User savedUser = userService.register(user);
		log.debug("Saved user: {}", savedUser);
		return "redirect:/registration/success";
	}

	@GetMapping("/success")
	public String success() {
		return "/registration/success";
	}

}

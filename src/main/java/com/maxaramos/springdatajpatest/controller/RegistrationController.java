package com.maxaramos.springdatajpatest.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maxaramos.springdatajpatest.model.UserForm;
import com.maxaramos.springdatajpatest.service.UserService;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private Logger log;

	@Autowired
	private UserService userService;

	@GetMapping
	public String form() {
		return "/register/form";
	}

	@PostMapping
	public String submit(@Valid UserForm userForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.debug("Invalid user: {}", userForm);
			return "/register/form";
		}

		userService.save(userForm);
		log.debug("Saved user: {}", userForm);
		return "redirect:/register/success";
	}

	@GetMapping("/success")
	public String success() {
		return "/register/success";
	}

	@ModelAttribute
	public UserForm getUserForm() {
		return new UserForm();
	}

}

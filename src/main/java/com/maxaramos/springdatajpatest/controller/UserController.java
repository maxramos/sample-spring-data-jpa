package com.maxaramos.springdatajpatest.controller;

import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maxaramos.springdatajpatest.model.GenderType;
import com.maxaramos.springdatajpatest.model.User;
import com.maxaramos.springdatajpatest.service.UserService;
import com.maxaramos.springdatajpatest.validation.ConstraintGroups.ChangePassword;
import com.maxaramos.springdatajpatest.validation.ConstraintGroups.Save;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private Logger log;

	@Autowired
	private Validator validator;

	@Autowired
	private UserService userService;

	@GetMapping("/profile")
	public String profile(Model model, HttpSession session) {
		User user = (User) session.getAttribute(User.LOGGED_IN_USER_ATTR);
		model.addAttribute("user", user);
		model.addAttribute("genders", GenderType.values());
		return "/user/profile";
	}

	@PostMapping("/save")
	public String save(User user, HttpSession session) {
		Set<ConstraintViolation<User>> violations = validator.validate(user, Save.class);

		if (!violations.isEmpty()) {
			log.debug("Invalid user: {}", user);
			return "/user/profile";
		}

		Long id = ((User) session.getAttribute(User.LOGGED_IN_USER_ATTR)).getId();
		User updatedUser = userService.save(id, user);
		session.setAttribute(User.LOGGED_IN_USER_ATTR, updatedUser);
		log.debug("Saved user: {}", updatedUser);
		return "redirect:/user/profile";
	}

	@GetMapping("/changePassword")
	public String changePasswordForm(Model model) {
		model.addAttribute("user", new User());
		return "/user/changePassword";
	}

	@PostMapping("/changePassword")
	public String changePassword(User user, HttpSession session) {
		Set<ConstraintViolation<User>> violations = validator.validate(user, ChangePassword.class);

		if (!violations.isEmpty()) {
			log.debug("Invalid user: {}", user);
			return "/user/profile";
		}

		Long id = ((User) session.getAttribute(User.LOGGED_IN_USER_ATTR)).getId();
		User updatedUser = userService.changePassword(id, user);
		session.setAttribute(User.LOGGED_IN_USER_ATTR, updatedUser);
		log.debug("Saved user: {}", updatedUser);
		return "redirect:/user/profile";
	}

}

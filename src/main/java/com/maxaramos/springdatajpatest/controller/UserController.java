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

import com.maxaramos.springdatajpatest.dto.UserForm;
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
		model.addAttribute("userForm", UserForm.fromUser(user));
		return "/user/profile";
	}

	@PostMapping("/save")
	public String save(UserForm userForm, HttpSession session) {
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, Save.class);

		if (!violations.isEmpty()) {
			log.debug("Invalid user: {}", userForm);
			return "/user/profile";
		}

		User user = (User) session.getAttribute(User.LOGGED_IN_USER_ATTR);
		user = userService.save(user.getId(), userForm);
		session.setAttribute(User.LOGGED_IN_USER_ATTR, user);
		log.debug("Saved user: {}", UserForm.fromUser(user));
		return "redirect:/user/profile";
	}

	@GetMapping("/changePassword")
	public String changePasswordForm(Model model) {
		model.addAttribute("userForm", new UserForm());
		return "/user/changePassword";
	}

	@PostMapping("/changePassword")
	public String changePassword(UserForm userForm, HttpSession session) {
		Set<ConstraintViolation<UserForm>> violations = validator.validate(userForm, ChangePassword.class);

		if (!violations.isEmpty()) {
			log.debug("Invalid user: {}", userForm);
			return "/user/profile";
		}

		User user = (User) session.getAttribute(User.LOGGED_IN_USER_ATTR);
		user = userService.changePassword(user.getId(), userForm);
		session.setAttribute(User.LOGGED_IN_USER_ATTR, user);
		log.debug("Saved user: {}", UserForm.fromUser(user));
		return "redirect:/user/profile";
	}

}

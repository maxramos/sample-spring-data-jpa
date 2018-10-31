package com.maxaramos.samplespringdatajpa.controller;

import javax.servlet.http.HttpSession;

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

import com.maxaramos.samplespringdatajpa.model.GenderType;
import com.maxaramos.samplespringdatajpa.model.User;
import com.maxaramos.samplespringdatajpa.service.UserService;
import com.maxaramos.samplespringdatajpa.validation.ConstraintGroups.ChangePassword;
import com.maxaramos.samplespringdatajpa.validation.ConstraintGroups.Save;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private Logger log;

	@Autowired
	private UserService userService;

	@ModelAttribute(name = "genders")
	public GenderType[] getGenders() {
		return GenderType.values();
	}

	@GetMapping("/list")
	public String list(Model model, HttpSession session) {
		User supervisor = (User) session.getAttribute(User.LOGGED_IN_USER_ATTR);
		model.addAttribute("users", userService.findAllBySupervisor(supervisor));
		return "/user/list";
	}

	@GetMapping("/profile")
	public String profile(Model model, HttpSession session) {
		User user = (User) session.getAttribute(User.LOGGED_IN_USER_ATTR);
		model.addAttribute("user", user);
		return "/user/profile";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("user", new User());
		return "/user/add";
	}

	@PostMapping("/add")
	public String register(@Validated User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			log.debug("Invalid user: {}", user);
			return "/user/add";
		}

		User savedUser = userService.register(user);
		log.debug("Saved user: {}", savedUser);
		return "redirect:/user/list";
	}

	@PostMapping("/save")
	public String save(@Validated(Save.class) User user, BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors()) {
			log.debug("Invalid user: {}", user);
			return "/user/profile";
		}

		Long id = ((User) session.getAttribute(User.LOGGED_IN_USER_ATTR)).getId();
		User updatedUser = userService.save(id, user);
		session.setAttribute(User.LOGGED_IN_USER_ATTR, updatedUser);
		log.debug("Updated user: {}", updatedUser);
		return "redirect:/user/profile";
	}

	@GetMapping("/changePassword")
	public String changePasswordForm(Model model) {
		model.addAttribute("user", new User());
		return "/user/changePassword";
	}

	@PostMapping("/changePassword")
	public String changePassword(@Validated(ChangePassword.class) User user, BindingResult bindingResult, HttpSession session) {
		if (bindingResult.hasErrors()) {
			log.debug("Invalid user: {}", user);
			return "/user/changePassword";
		}

		Long id = ((User) session.getAttribute(User.LOGGED_IN_USER_ATTR)).getId();
		User updatedUser = userService.changePassword(id, user);
		session.setAttribute(User.LOGGED_IN_USER_ATTR, updatedUser);
		log.debug("Updated user: {}", updatedUser);
		return "redirect:/user/profile";
	}

}

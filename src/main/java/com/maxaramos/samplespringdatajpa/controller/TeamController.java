package com.maxaramos.samplespringdatajpa.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.maxaramos.samplespringdatajpa.model.User;
import com.maxaramos.samplespringdatajpa.service.TeamService;

@Controller
@RequestMapping("/team")
public class TeamController {

	@Autowired
	private TeamService teamService;

	@GetMapping("/list")
	public String list(Model model, HttpSession session) {
		User supervisor = (User) session.getAttribute(User.LOGGED_IN_USER_ATTR);
		model.addAttribute("teams", teamService.findAllBySupervisor(supervisor));
		return "/team/list";
	}

}

package com.maxaramos.springdatajpatest.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.maxaramos.springdatajpatest.jsonview.TeamMemberView;
import com.maxaramos.springdatajpatest.jsonview.TeamView;
import com.maxaramos.springdatajpatest.model.User;
import com.maxaramos.springdatajpatest.service.TeamService;

@RestController
@RequestMapping("/api/teams")
public class TeamRestController {

	@Autowired
	private TeamService teamService;

	@GetMapping("/{supervisorId}")
	@JsonView(TeamView.class)
	public List<User> findAllBySupervisorId(@PathVariable("supervisorId") Long supervisorId) {
		return teamService.findAllBySupervisorId(supervisorId);
	}

	@GetMapping("/{supervisorId}/{teamId}")
	@JsonView(TeamMemberView.class)
	public User findById(@PathVariable("supervisorId") Long supervisorId, @PathVariable("teamId") Long teamId) {
		return teamService.findById(supervisorId, teamId);
	}

}

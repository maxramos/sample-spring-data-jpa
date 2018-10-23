package com.maxaramos.springdatajpatest.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.maxaramos.springdatajpatest.jsonview.TeamMemberView;
import com.maxaramos.springdatajpatest.jsonview.TeamView;
import com.maxaramos.springdatajpatest.model.User;
import com.maxaramos.springdatajpatest.service.TeamService;

@RestController("/api/teams")
public class TeamController {

	@Autowired
	private TeamService teamService;

	@GetMapping("/{supervisorId}")
	@JsonView(TeamView.class)
	public List<User> findAllBySupervisorId(Long supervisorId) {
		return teamService.findAllBySupervisorId(supervisorId);
	}

	@GetMapping("/{supervisorId}/{teamId}")
	@JsonView(TeamMemberView.class)
	public User findById(Long supervisorId, Long teamId) {
		return teamService.findById(supervisorId, teamId);
	}

}

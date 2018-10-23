package com.maxaramos.springdatajpatest.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maxaramos.springdatajpatest.dao.UserDao;
import com.maxaramos.springdatajpatest.model.User;

@Service
@Transactional
public class TeamService {

	@Autowired
	private UserDao userDao;

	public List<User> findAllBySupervisor(User supervisor) {
		User savedSupervisor = userDao.findById(supervisor.getId()).orElseThrow(() -> new UserNotFoundException("Id: " + supervisor.getId()));
		List<User> teams = new ArrayList<>();

		if ("admin".equals(savedSupervisor.getRole())) {
			for (User departmentHead : savedSupervisor.getSupervisees()) {
				teams.addAll(userDao.findAllBySupervisor(departmentHead));
			}
		} else if ("depthead".equals(savedSupervisor.getRole())) {
			teams.addAll(userDao.findAllBySupervisor(supervisor));
		}

		return teams;
	}

	public List<User> findAllBySupervisorId(Long id) {
		User supervisor = userDao.findById(id).orElseThrow(() -> new UserNotFoundException("Id: " + id));
		return findAllBySupervisor(supervisor);
	}

	public User findById(Long id) {
		return userDao.findById(id).orElse(null);
	}

}

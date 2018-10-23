package com.maxaramos.springdatajpatest.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import com.fasterxml.jackson.annotation.JsonView;
import com.maxaramos.springdatajpatest.jsonview.BasicView;
import com.maxaramos.springdatajpatest.jsonview.DepartmentView;
import com.maxaramos.springdatajpatest.validation.ConstraintGroups.Save;

@Entity
@Table(name = "departments")
public class Department {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	@Size(min = 1, max = 20, groups = { Default.class, Save.class })
	private String name;

	@OneToOne
	@JoinTable(name = "departments_users",
			joinColumns = @JoinColumn(name = "department_id", table = "depatments", referencedColumnName = "id"),
			inverseJoinColumns= @JoinColumn(name = "head_id", table = "users", referencedColumnName = "id"))
	private User head;

	@OneToMany(mappedBy = "department")
	private List<User> members = new ArrayList<>();

	@JsonView({ BasicView.class, DepartmentView.class })
	public Long getId() {
		return id;
	}

	@JsonView({ BasicView.class, DepartmentView.class })
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User getHead() {
		return head;
	}

	public void setHead(User head) {
		this.head = head;
	}

	@JsonView({ BasicView.class, DepartmentView.class })
	public String getHeadFullName() {
		return head.getFullName();
	}

	@JsonView(DepartmentView.class)
	public List<User> getMembers() {
		return members;
	}

	@JsonView({ BasicView.class, DepartmentView.class })
	public int getDepartmentSize() {
		return members.size();
	}

	@Override
	public String toString() {
		return String.format("Department [id=%s, name=%s]", id, name);
	}

}

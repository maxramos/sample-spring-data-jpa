package com.maxaramos.samplespringdatajpa.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonView;
import com.maxaramos.samplespringdatajpa.jsonview.BasicView;
import com.maxaramos.samplespringdatajpa.jsonview.DepartmentView;
import com.maxaramos.samplespringdatajpa.jsonview.TeamMemberView;
import com.maxaramos.samplespringdatajpa.jsonview.TeamView;
import com.maxaramos.samplespringdatajpa.jsonview.UserView;
import com.maxaramos.samplespringdatajpa.validation.ConstraintGroups.ChangePassword;
import com.maxaramos.samplespringdatajpa.validation.ConstraintGroups.Save;

@Entity
@Table(name = "users")
public class User implements UserDetails {

	public static final String LOGGED_IN_USER_ATTR = "loggedInUser";

	private static final long serialVersionUID = 2994511031102491790L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "username")
	@Size(min = 1, max = 20, groups = { Default.class, Save.class })
	private String username;

	@Column(name = "password")
	private String password;

	@Transient
	@Size(min = 4, max = 16, groups = { Default.class, ChangePassword.class })
	private String rawPassword;

	@Transient
	@Size(min = 4, max = 16, groups = { Default.class, ChangePassword.class })
	private String confirmRawPassword;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_authorities",
			joinColumns = @JoinColumn(name = "user_id", table = "users", referencedColumnName = "id"),
			inverseJoinColumns= @JoinColumn(name = "authority_id", table = "authorities", referencedColumnName = "id"))
	private Set<Authority> authorities = new HashSet<>();

	@Column(name = "enabled")
	private Boolean enabled = false;

	@Column(name = "email")
	@Size(min = 5, max = 30, groups = { Default.class, Save.class })
	@Email(groups = { Default.class, Save.class })
	private String email;

	@Column(name = "first_name")
	@Size(min = 1, max = 20, groups = { Default.class, Save.class })
	private String firstName;

	@Column(name = "last_name")
	@Size(min = 1, max = 20, groups = { Default.class, Save.class })
	private String lastName;

	@Column(name = "age")
	@NotNull(groups = { Default.class, Save.class })
	@Positive(groups = { Default.class, Save.class })
	private Integer age;

	@Column(name = "birthday")
	@NotNull(groups = { Default.class, Save.class })
	@Past(groups = { Default.class, Save.class })
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate birthday;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	@NotNull(groups = { Default.class, Save.class })
	private GenderType gender;

	@Column(name = "contact_number")
	@Size(min = 1, max = 20, groups = { Default.class, Save.class })
	private String contactNumber;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "address_id")
	@Valid
	private Address address = new Address();

	@ManyToOne
	@JoinColumn(name = "supervisor_id")
	private User supervisor;

	@OneToMany(mappedBy = "supervisor")
	private List<User> supervisees = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "department_id")
	private Department department;

	public void addAuthority(Authority authority) {
		authorities.add(authority);
	}

	@JsonView({ BasicView.class, UserView.class, TeamView.class, TeamMemberView.class })
	public Long getId() {
		return id;
	}

	@Override
	@JsonView({ BasicView.class, UserView.class })
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	@JsonView(UserView.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRawPassword() {
		return rawPassword;
	}

	public void setRawPassword(String rawPassword) {
		this.rawPassword = rawPassword;
	}

	public String getConfirmRawPassword() {
		return confirmRawPassword;
	}

	public void setConfirmRawPassword(String confirmRawPassword) {
		this.confirmRawPassword = confirmRawPassword;
	}

	@Override
	public Set<Authority> getAuthorities() {
		return authorities;
	}

	@JsonView({ BasicView.class, UserView.class })
	public String getRole() {
		Optional<Authority> authority = authorities.stream().findAny();
		String role = null;

		if (authority.isPresent()) {
			role = authority.get().getAuthority().split("_")[1].toLowerCase();
		}

		return role;
	}

	@Override
	@JsonView({ BasicView.class, UserView.class })
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@JsonView({ BasicView.class, UserView.class })
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JsonView({ BasicView.class, UserView.class })
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@JsonView({ BasicView.class, UserView.class })
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonView({ TeamView.class, TeamMemberView.class, DepartmentView.class })
	public String getFullName() {
		return firstName + " " + lastName;
	}

	@JsonView({ BasicView.class, UserView.class })
	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@JsonView({ BasicView.class, UserView.class })
	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	@JsonView({ BasicView.class, UserView.class })
	public GenderType getGender() {
		return gender;
	}

	public void setGender(GenderType gender) {
		this.gender = gender;
	}

	@JsonView({ BasicView.class, UserView.class })
	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	@JsonView(UserView.class)
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(User supervisor) {
		this.supervisor = supervisor;
	}

	@JsonView(UserView.class)
	public String getSupervisorFullName() {
		if (supervisor == null) {
			return null;
		}

		return supervisor.getFullName();
	}

	public List<User> getSupervisees() {
		return supervisees;
	}

	@JsonView(TeamMemberView.class)
	public List<String> getSuperviseeFullNames() {
		return supervisees.stream().map(supervisee -> supervisee.getFullName()).collect(Collectors.toList());
	}

	@JsonView({ TeamView.class, TeamMemberView.class })
	public int getTeamSize() {
		return supervisees.size();
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@JsonView(UserView.class)
	public String getDepartmentName() {
		if (department == null) {
			return null;
		}

		return department.getName();
	}

	@Override
	public String toString() {
		return String.format("User [id=%s, username=%s, enabled=%s, email=%s, firstName=%s, lastName=%s, age=%s, birthday=%s, gender=%s, contactNumber=%s]", id, username, enabled,
				email, firstName, lastName, age, birthday, gender, contactNumber);
	}

}

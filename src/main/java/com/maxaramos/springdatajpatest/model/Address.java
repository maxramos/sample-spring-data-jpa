package com.maxaramos.springdatajpatest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.util.StringUtils;

@Entity
@Table(name = "addresses")
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "address1")
	private String address1;

	@Column(name = "address2")
	private String address2;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;

	@Column(name = "zip_code")
	private String zipCode;

	public String getFormattedAddress() {
		StringBuilder builder = new StringBuilder();
		builder.append(address1).append("<br />");

		if (!StringUtils.isEmpty(address2)) {
			builder.append(address1).append("<br />");
		}

		builder.append(city).append("<br />");
		builder.append(state).append("<br />");
		builder.append(country).append("<br />");
		builder.append(zipCode);
		return builder.toString();
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Address [id=%s, address1=%s, address2=%s, city=%s, state=%s, country=%s, zipCode=%s]", id, address1, address2, city, state, country, zipCode);
	}

}

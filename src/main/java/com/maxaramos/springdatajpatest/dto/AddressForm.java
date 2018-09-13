package com.maxaramos.springdatajpatest.dto;

import javax.validation.constraints.Size;
import javax.validation.groups.Default;

import com.maxaramos.springdatajpatest.model.Address;
import com.maxaramos.springdatajpatest.validation.ConstraintGroups.Save;

public class AddressForm {

	@Size(min = 1, max = 100, groups = { Default.class, Save.class })
	private String address1;

	@Size(max = 100, groups = { Default.class, Save.class })
	private String address2;

	@Size(min = 1, max = 20, groups = { Default.class, Save.class })
	private String city;

	@Size(min = 1, max = 20, groups = { Default.class, Save.class })
	private String state;

	@Size(min = 1, max = 20, groups = { Default.class, Save.class })
	private String country;

	@Size(min = 1, max = 10, groups = { Default.class, Save.class })
	private String zipCode;

	public static AddressForm fromAddress(Address address) {
		AddressForm addressForm = new AddressForm();
		addressForm.address1 = address.getAddress1();
		addressForm.address2 = address.getAddress2();
		addressForm.city = address.getCity();
		addressForm.state = address.getState();
		addressForm.country = address.getCountry();
		addressForm.zipCode = address.getZipCode();
		return addressForm;
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

	@Override
	public String toString() {
		return String.format("AddressForm [address1=%s, address2=%s, city=%s, state=%s, country=%s, zipCode=%s]", address1, address2, city, state, country, zipCode);
	}

}

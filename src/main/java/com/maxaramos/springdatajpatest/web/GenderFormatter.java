package com.maxaramos.springdatajpatest.web;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;

import com.maxaramos.springdatajpatest.model.GenderType;

public class GenderFormatter implements Formatter<GenderType> {

	@Override
	public String print(GenderType object, Locale locale) {
		return StringUtils.capitalize(object.toString().toLowerCase());
	}

	@Override
	public GenderType parse(String text, Locale locale) throws ParseException {
		return GenderType.valueOf(text.toUpperCase());
	}

}

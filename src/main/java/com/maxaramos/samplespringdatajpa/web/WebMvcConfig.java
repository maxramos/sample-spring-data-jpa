package com.maxaramos.samplespringdatajpa.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.maxaramos.samplespringdatajpa.formatter.GenderFormatter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new GenderFormatter());
	}

}

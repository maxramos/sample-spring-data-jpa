package com.maxaramos.springdatajpatest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

	@Configuration
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public static class WsSecurityConfig extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.antMatcher("/api/**")
				.authorizeRequests()
					.antMatchers(HttpMethod.POST).hasRole("ADMIN")
					.antMatchers(HttpMethod.PUT).hasRole("ADMIN")
					.antMatchers(HttpMethod.DELETE).hasRole("ADMIN")
					.anyRequest().authenticated()
					.and()
				.httpBasic()
					.and()
				.csrf().disable();
		}

	}

	@Configuration
	public static class UiSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private LoginSuccessHandler loginSuccessHandler;

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
					.antMatchers("/css/**", "/js/**", "/img/**", "/webjars/**", "/login").permitAll()
					.antMatchers("/admin/**", "/registration/**", "/department/**").hasRole("ADMIN")
					.antMatchers("/team/**").hasAnyRole("ADMIN", "DEPTHEAD")
					.antMatchers("/user/list").hasAnyRole("ADMIN", "DEPTHEAD", "SUPERVISOR")
					.anyRequest().authenticated()
					.and()
				.formLogin()
					.loginPage("/login")
					.successHandler(loginSuccessHandler)
					.and()
				.headers()
					.frameOptions().sameOrigin()
					.and()
				.csrf().disable();
		}

	}

}





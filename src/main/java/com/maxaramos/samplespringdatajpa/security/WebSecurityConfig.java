package com.maxaramos.samplespringdatajpa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

import com.maxaramos.samplespringdatajpa.service.UserService;

@EnableWebSecurity
public class WebSecurityConfig {

	@Value("${ssdj.security.realm-name}")
	private String realmName;

	@Value("${ssdj.security.digest.key}")
	private String digestKey;

	@Bean
	public PasswordEncoder passwordEncoder() {
		// For non digest auth.
		return new BCryptPasswordEncoder();

		// For digest auth only.
//		return NoOpPasswordEncoder.getInstance();
	}

	@Bean
	public BasicAuthenticationEntryPoint basicAuthenticationEntryPoint() {
		BasicAuthenticationEntryPoint entryPoint = new BasicAuthenticationEntryPoint();
		entryPoint.setRealmName(realmName);
		return entryPoint;
	}

	@Bean
	public DigestAuthenticationEntryPoint digestAuthenticationEntryPoint() {
		DigestAuthenticationEntryPoint entryPoint = new DigestAuthenticationEntryPoint();
		entryPoint.setRealmName(realmName);
		entryPoint.setKey(digestKey);
		return entryPoint;
	}

	@Bean
	public DigestAuthenticationFilter digestAuthenticationFilter(UserService userService) {
		DigestAuthenticationFilter filter = new DigestAuthenticationFilter();
		filter.setAuthenticationEntryPoint(digestAuthenticationEntryPoint());
		filter.setUserDetailsService(userService);
		return filter;
	}

	@Configuration
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public static class WsSecurityConfig extends WebSecurityConfigurerAdapter {

		@Autowired
		private BasicAuthenticationEntryPoint basicAuthenticationEntryPoint;

		@Autowired
		private DigestAuthenticationEntryPoint digestAuthenticationEntryPoint;

		@Autowired
		private DigestAuthenticationFilter digestAuthenticationFilter;

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
//				.addFilterAt(digestAuthenticationFilter, BasicAuthenticationFilter.class)
				.exceptionHandling()
					.authenticationEntryPoint(basicAuthenticationEntryPoint)
//					.authenticationEntryPoint(digestAuthenticationEntryPoint)
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





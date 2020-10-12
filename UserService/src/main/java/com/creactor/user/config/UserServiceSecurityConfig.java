package com.creactor.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.creactor.user.service.UserService;

@Configuration
@EnableWebSecurity
public class UserServiceSecurityConfig extends WebSecurityConfigurerAdapter {

	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(16);
	
	@Autowired
	private UserService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return this.passwordEncoder;
	}

	// @formatter:off
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/api/public/*").permitAll()
			.anyRequest().authenticated()
			.and()
			.csrf().disable()
			.httpBasic();
		
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		return this.userDetailsService;
	}
	// @formatter:on
}

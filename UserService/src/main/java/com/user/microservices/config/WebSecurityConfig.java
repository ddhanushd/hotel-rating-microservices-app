package com.user.microservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {
	
	@Bean
	public SecurityFilterChain chain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.authorizeHttpRequests()
		             .anyRequest()
		             .authenticated()
		             .and()
		             .oauth2ResourceServer()
		             .jwt();
		
		return httpSecurity.build();
	}

}

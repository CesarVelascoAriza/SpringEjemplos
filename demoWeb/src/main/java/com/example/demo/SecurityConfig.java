package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(authorize -> authorize
					.requestMatchers("/", "/error").permitAll()
					.requestMatchers(org.springframework.http.HttpMethod.GET, "/personas/**")
					.hasAuthority("SCOPE_users.read")
					.requestMatchers(org.springframework.http.HttpMethod.POST, "/personas/**")
					.hasAuthority("SCOPE_users.write")
					.anyRequest().authenticated())
			.oauth2ResourceServer(oauth2 -> oauth2.jwt());
		return http.build();
	}

}

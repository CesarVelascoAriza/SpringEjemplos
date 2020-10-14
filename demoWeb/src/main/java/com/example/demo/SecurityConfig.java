package com.example.demo;

import java.security.CryptoPrimitive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bcrypt;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*propiedad basica para quemar los roles 
		 * //auth.inMemoryAuthentication().withUser("").password("").roles("").and().withUser("").password("").roles("");
		 * */
		auth.userDetailsService(userDetailsService).passwordEncoder(bcrypt);

	}
	protected void configure(HttpSecurity http) throws Exception  {
		http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
		
	}

}

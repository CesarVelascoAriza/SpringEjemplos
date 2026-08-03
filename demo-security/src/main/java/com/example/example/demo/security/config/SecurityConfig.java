package com.example.example.demo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
public class SecurityConfig {

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http,JWTValidationFilter jwtValidationFilter) throws Exception {
        //var requesthandler =new CsrfTokenRequestHandler();
        //requesthandler.setCsrfRequestAttributeName("_csrf");
        //http.csrf(csrf -> csrf.csrfTokenRequestHandler(requesthandler));
        http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authorizeHttpRequests(aut->
            aut.requestMatchers("/Authenticated").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(Customizer.withDefaults())
        .httpBasic(Customizer.withDefaults())
        .addFilterAfter(jwtValidationFilter, BasicAuthenticationFilter.class)
        ;
            
        http.csrf(crsf->
            crsf.csrfTokenRequestHandler(null)
            .ignoringRequestMatchers("/Authenticated")
            .ignoringRequestMatchers("/pruebas")
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
            //.addfilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class)
            ;
        return http.build();
    }
}

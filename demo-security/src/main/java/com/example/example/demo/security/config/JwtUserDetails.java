package com.example.example.demo.security.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.example.demo.security.repository.UserRepositoryCustom;

import lombok.AllArgsConstructor;

import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class JwtUserDetails implements UserDetailsService {

    private final UserRepositoryCustom customUserDetailsService;


    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
       return customUserDetailsService.findUserWithAuthorities(username).map(user -> {
           var authorities = user.getAuthorities().stream()
                   .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                   .collect(Collectors.toList());

            return new User(user.getUsername(), user.getPassword(), authorities);
        }).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username)) ;
    }

}

package com.example.example.demo.security.repository;

import java.util.Optional;

import com.example.example.demo.security.entiti.User;

public interface UserRepositoryCustom {
     Optional<User> findUserWithAuthorities(String username);
}

package com.example.example.demo.security.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.example.demo.security.entiti.User;

@Primary
public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom {
}


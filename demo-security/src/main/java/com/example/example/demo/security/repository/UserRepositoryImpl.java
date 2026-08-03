package com.example.example.demo.security.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.example.demo.security.entiti.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> findUserWithAuthorities(String username) {
        return entityManager.createQuery(
                "SELECT u FROM User u LEFT JOIN FETCH u.authorities WHERE u.username = :username",
                User.class
        )
        .setParameter("username", username)
        .getResultStream()
        .findFirst(); // devuelve Optional<User>
    }
}
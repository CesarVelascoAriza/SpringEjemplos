package com.example.example.demo.security.controller;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * JwtResponse
 */
@Data
@AllArgsConstructor
public class JwtResponse {
    private final String token;
}

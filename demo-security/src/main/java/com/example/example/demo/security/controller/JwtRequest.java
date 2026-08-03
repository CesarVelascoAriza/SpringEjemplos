package com.example.example.demo.security.controller;

import lombok.Data;

/**
 * JwtRequest
 */
@Data
public class JwtRequest {

    private String username;
    private String password;

}

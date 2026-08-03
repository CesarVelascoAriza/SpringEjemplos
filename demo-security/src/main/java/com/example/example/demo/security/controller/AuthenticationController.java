package com.example.example.demo.security.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.example.demo.security.config.JwtService;
import com.example.example.demo.security.config.JwtUserDetails;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager aunthenticationManager;
    private final JwtUserDetails jwtDetails;
    private final JwtService jwtService;

    @PostMapping("/Authenticated")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        authenticate(jwtRequest);
        var userDetails = jwtDetails.loadUserByUsername(jwtRequest.getUsername());

        var token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @GetMapping("/pruebas")
    public String getMethodName() {
        return new String("pruebas");
    }
    
    private void authenticate(JwtRequest jwtRequest) throws Exception {
        try {
            aunthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}

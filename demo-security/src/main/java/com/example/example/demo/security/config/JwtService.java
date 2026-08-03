package com.example.example.demo.security.config;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.example.demo.security.enums.JwtConstantes;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private Claims getAllClaimsFromToken( String token) {
        var key = Keys.hmacShaKeyFor(JwtConstantes.SECRET.getSecret().getBytes(StandardCharsets.UTF_8));
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public <T>  T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(getAllClaimsFromToken(token));
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String getUserNameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }
    public Boolean validateToken(String token,UserDetails userDetails) {
        var useraname = userDetails.getUsername();
        var usernameFromToken = getUserNameFromToken(token);
        return (usernameFromToken.equals(useraname) && !isTokenExpired(token));
    }

    private String getToken(Map<String, Object> claims, String Subject) {
        var key = Keys.hmacShaKeyFor(JwtConstantes.SECRET.getSecret().getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(Subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JwtConstantes.VALIDITY.getValidity()))
                .signWith(key)
                .compact();
    }
      
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims =Collections.singletonMap("Roles", userDetails.getAuthorities().toString());
        return getToken(claims, userDetails.getUsername());
    }
}

package com.group2.theminimart.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.group2.theminimart.security.JwtProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    // private static final String secretPhrase = "SECRET@#$%^&*()12345678op2323423";
    // private static final SecretKey KEY = Keys.hmacShaKeyFor(secretPhrase.getBytes());
    private SecretKey KEY;
    private long jwtTokenExpirationInMs = 3600000;

    public JwtUtil(JwtProperties jwtProperties) {
        this.KEY = Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes());
    }
    

    public String generateToken(Authentication authentication) {
        // UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        UserDetails user = (UserDetails) authentication.getPrincipal();
        return Jwts.builder()
            .subject(authentication.getName())
            .claim(
                "roles",
                user.getAuthorities())
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + jwtTokenExpirationInMs))
            .signWith(KEY)
            .compact();
    }

    public String extractUsername(String token) {
        
        return Jwts.parser()
            .verifyWith(KEY)           
            .build()                            
            .parseSignedClaims(token)              
            .getPayload()                                 
            .getSubject();
        }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
            .verifyWith(KEY)
            .build()
            .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            throw new RuntimeException("token validation failed");
        }
    }

    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
            .verifyWith(KEY)
            .build()
            .parseSignedClaims(token)
            .getPayload();
        } catch(IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid JWT Token");
        }
    }
    
    public List<String> extractRoles(String token) {
    Claims claims = extractAllClaims(token); 
    Object rolesObject = claims.get("roles");

    List<String> roles = new ArrayList<>();
    if (rolesObject instanceof List<?>) {
        for (Object role : (List<?>) rolesObject) {
            roles.add(role.toString());
        }
    }
    return roles;
}
}

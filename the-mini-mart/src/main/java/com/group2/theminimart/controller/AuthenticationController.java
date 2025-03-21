package com.group2.theminimart.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group2.theminimart.dto.JwtResponseDto;
import com.group2.theminimart.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) { 
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponseDto> authenticateUser(HttpServletRequest request) {
        // Get the 'Authorization' header from the request
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Basic ")) {
            throw new RuntimeException("Missing or incorrect Authorization header");
        }

        // Decode the Base64 encoded username:password
        String base64Credentials = authHeader.substring(6); // Remove "Basic " prefix
        String credentials = new String(Base64.getDecoder().decode(base64Credentials), StandardCharsets.UTF_8);
        final String[] values = credentials.split(":", 2);
        String username = values[0];
        String password = values[1];

        // Authenticate using the extracted username and password
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(username, password)
        );

        System.out.println("Authentication successful: " + authentication);
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Return the JWT token in the response
        return new ResponseEntity<>(new JwtResponseDto(jwtUtil.generateToken(authentication)), HttpStatus.OK);
    }
}

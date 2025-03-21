package com.group2.theminimart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group2.theminimart.dto.UserLoginRequestDto;
import com.group2.theminimart.dto.UserRegisterRequestDto;
import com.group2.theminimart.dto.UserResponseDto;
import com.group2.theminimart.dto.UserWithTokenResponseDto;
import com.group2.theminimart.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthController {

  private UserService userService;

  public AuthController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<UserResponseDto> registerUser(@Valid @RequestBody UserRegisterRequestDto user) {
    return new ResponseEntity<>(userService.registerUser(user),
        HttpStatus.CREATED);
  }

  @PostMapping("/login")
  public ResponseEntity<UserWithTokenResponseDto> loginUser(@Valid @RequestBody UserLoginRequestDto user) {
    return new ResponseEntity<>(userService.loginUser(user), HttpStatus.OK);
  }

}

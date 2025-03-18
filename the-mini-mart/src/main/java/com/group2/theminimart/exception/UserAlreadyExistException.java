package com.group2.theminimart.exception;

public class UserAlreadyExistException extends RuntimeException {

  public UserAlreadyExistException() {
    super("Username already exist. Please choose another username.");
  }

}

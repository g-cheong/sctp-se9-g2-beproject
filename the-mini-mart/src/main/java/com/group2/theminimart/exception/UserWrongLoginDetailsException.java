package com.group2.theminimart.exception;

public class UserWrongLoginDetailsException extends RuntimeException {
  public UserWrongLoginDetailsException() {
    super("Invalid username or password.");
  }
}

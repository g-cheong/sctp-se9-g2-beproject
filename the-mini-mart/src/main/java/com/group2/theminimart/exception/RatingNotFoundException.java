package com.group2.theminimart.exception;

public class RatingNotFoundException extends RuntimeException {
    public RatingNotFoundException(Long id) {
        super("Could not find user with id: " + id);
    }
}

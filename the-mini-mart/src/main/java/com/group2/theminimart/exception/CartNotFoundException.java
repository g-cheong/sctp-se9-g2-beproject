package com.group2.theminimart.exception;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String id) {
        super("Could not find user with id: " + id);
    }
}

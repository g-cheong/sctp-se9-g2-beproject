package com.group2.theminimart.exception;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String id) {
        super("Cart with id: " + id + " was not found in the records.");
    }
}

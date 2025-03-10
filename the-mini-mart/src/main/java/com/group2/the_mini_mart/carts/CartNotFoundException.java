package com.group2.the_mini_mart.carts;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String id){
        super("Cart with id: " + id + " was not found in the records.");
    }
}

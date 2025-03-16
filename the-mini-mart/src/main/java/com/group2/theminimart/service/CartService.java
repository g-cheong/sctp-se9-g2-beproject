package com.group2.theminimart.service;

import java.util.ArrayList;

import com.group2.theminimart.entity.Cart;

public interface CartService {
    // add business logic
    public Cart createCart(Cart cart);

    public ArrayList<Cart> getCarts();

    public Cart getCart(Long id);

    public Cart updateCart(Long id, Cart cart);

    public void deleteCart(Long id);
}

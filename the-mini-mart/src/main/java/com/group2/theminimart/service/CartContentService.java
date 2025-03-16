package com.group2.theminimart.service;

import java.util.ArrayList;

import com.group2.theminimart.entity.CartContent;

public interface CartContentService {
    // add business logic
    public CartContent createCartContent(CartContent cart);

    public ArrayList<CartContent> getCartContents();

    public CartContent getCartContent(Long id);

    public CartContent updateCartContent(Long id, CartContent cartContent);

    public void deleteCartContent(Long id);
}

package com.group2.theminimart.controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group2.theminimart.entity.Cart;
import com.group2.theminimart.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    };

    @PostMapping
    public Cart createCart(@RequestBody Cart cart) {
        return cartService.createCart(cart);
    }

    @GetMapping
    public ArrayList<Cart> getCarts() {
        return cartService.getCarts();
    }

    @GetMapping("/{id}")
    public Cart getCart(@PathVariable Long id) {
        return cartService.getCart(id);
    }

    @PutMapping("/{id}")
    public Cart updateCart(@PathVariable Long id, @RequestBody Cart cart) {
        return cartService.updateCart(id, cart);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id) {
        cartService.deleteCart(id);
    }
}

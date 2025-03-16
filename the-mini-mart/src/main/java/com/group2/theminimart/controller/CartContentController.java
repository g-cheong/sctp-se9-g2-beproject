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

import com.group2.theminimart.entity.CartContent;
import com.group2.theminimart.service.CartContentServiceImpl;


@RestController
@RequestMapping("/cartcontent")
public class CartContentController {
    private CartContentServiceImpl cartContentService;

    public CartContentController(CartContentServiceImpl cartContentService) {
        this.cartContentService = cartContentService;
    };

    @PostMapping
    public CartContent createCartContent(@RequestBody CartContent cartContent) {
        return cartContentService.createCartContent(cartContent);
    }

    @GetMapping
    public ArrayList<CartContent> getCartContents() {
        return cartContentService.getCartContents();
    }

    @GetMapping("/{id}")
    public CartContent getCartContent(@PathVariable Long id) {
        return cartContentService.getCartContent(id);
    }

    @PutMapping("/{id}")
    public CartContent updateCartContent(@PathVariable Long id, @RequestBody CartContent cartContent) {
        return cartContentService.updateCartContent(id, cartContent);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id) {
        cartContentService.deleteCartContent(id);
    }
}

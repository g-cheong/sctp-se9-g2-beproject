package com.group2.theminimart.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group2.theminimart.dto.CartDto;
import com.group2.theminimart.entity.CartContent;
import com.group2.theminimart.service.CartContentService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/cart")
public class CartContentController {
    private CartContentService cartContentService;

    public CartContentController(CartContentService cartContentService) {
        this.cartContentService = cartContentService;
    };

    // create 
    @PostMapping
    public ResponseEntity<CartContent> createCartContent(@Valid @RequestBody CartContent cartContent) {
        return new ResponseEntity<>(cartContentService.createCartContent(cartContent), HttpStatus.ACCEPTED);
    }

    // read
    // @GetMapping
    // public ResponseEntity<ArrayList<CartContent>> getCartContents() {
    //     return new ResponseEntity<>(cartContentService.getCartContents(), HttpStatus.OK);
    // }

    @GetMapping("/{id}")
    public ResponseEntity<CartDto> getCartContent(@PathVariable Long userId) {
        return new ResponseEntity<>(cartContentService.getCartContent(userId), HttpStatus.OK);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<CartDto> updateCartContent(@PathVariable Long id, @Valid @RequestBody CartDto cartDto) {
        return new ResponseEntity<>(cartContentService.updateCartContent(id, cartDto), HttpStatus.ACCEPTED);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCart(@PathVariable Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

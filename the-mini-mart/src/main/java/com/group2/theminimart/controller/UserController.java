package com.group2.theminimart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group2.theminimart.dto.CartDto;
import com.group2.theminimart.dto.RatingRequestDto;
import com.group2.theminimart.dto.RatingResponseDto;
import com.group2.theminimart.dto.SampleCartDto;
import com.group2.theminimart.dto.UserResponseDto;
import com.group2.theminimart.dto.UserUpdateRequestDto;
import com.group2.theminimart.service.CartContentService;
import com.group2.theminimart.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/users")
public class UserController {
    private UserService userService;
    private CartContentService cartContentService;

    public UserController(UserService userService, CartContentService cartContentService) {
        this.userService = userService;
        this.cartContentService = cartContentService;
    }

    // -------------- USERS CRUD OPERATIONS --------------
    // USER CREATE
    // Created at AuthController register

    // USER READ
    // admin feature
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    // admin feature
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    // USER UPDATE
    @PutMapping
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(userService.updateUserPassword(username, userUpdateRequestDto), HttpStatus.OK);
    }

    // USER DELETE
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // -------------- RATINGS CRUD OPERATIONS --------------

    // RATINGS CREATE
    @PostMapping("/ratings/products/{productId}")
    public ResponseEntity<RatingResponseDto> createProductRating(@PathVariable Long productId,
            @Valid @RequestBody RatingRequestDto ratingDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(userService.addProductRating(username, productId,
                ratingDto), HttpStatus.CREATED);
    }

    // RATINGS READ
    @GetMapping("/ratings")
    public ResponseEntity<List<RatingResponseDto>> getUserRatings() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(userService.getUserRatings(username), HttpStatus.OK);
    }

    @GetMapping("/ratings/products/{productId}")
    public ResponseEntity<RatingResponseDto> getUserRating(@PathVariable Long productId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(userService.getUserRatingByProductId(username, productId), HttpStatus.OK);
    }

    // RATINGS UPDATE
    @PutMapping("/ratings/products/{productId}")
    public ResponseEntity<RatingResponseDto> updateProductRating(@PathVariable Long productId,
            @Valid @RequestBody RatingRequestDto ratingDto) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(userService.updateProductRating(username, productId, ratingDto), HttpStatus.OK);
    }

    // RATINGS DELETE
    @DeleteMapping("/ratings/products/{productId}")
    public ResponseEntity<HttpStatus> deleteProductRating(@PathVariable Long productId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.deleteProductRating(username, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // -------------- RATINGS CRUD OPERATIONS --------------

    // CART CREATE
    @PostMapping("/{userId}/cart")
    public ResponseEntity<CartDto> createCartContent(@Valid @PathVariable Long userId,
            @Valid @RequestBody CartDto cartDto) {
        return new ResponseEntity<>(cartContentService.createCartContent(userId, cartDto), HttpStatus.CREATED);
    }

    // CART READ
    @GetMapping("/cart/sample")
    public ResponseEntity<List<SampleCartDto>> getUserCart() {
        SampleCartDto cartproduct1 = SampleCartDto.builder().id(1L)
                .title("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops").price(109.95)
                .description(
                        "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday")
                .image("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg").quantity(1).total(109.95).build();

        return new ResponseEntity<>(List.of(cartproduct1), HttpStatus.OK);
    }

    @GetMapping("/{userId}/cart")
    public ResponseEntity<List<CartDto>> getUserCart(@Valid @PathVariable Long userId) {
        return new ResponseEntity<>(cartContentService.getCartContents(userId), HttpStatus.OK);
    }

    // CART UPDATE
    @PutMapping("/{userId}/cart")
    public ResponseEntity<List<CartDto>> updateCart(@Valid @PathVariable Long userId,
            @Valid @RequestBody List<CartDto> cartDtoList) {
        return new ResponseEntity<>(cartContentService.updateCart(userId, cartDtoList), HttpStatus.OK);
    }

    @PutMapping("/{userId}/cart/{productId}")
    public ResponseEntity<CartDto> updateCartContent(@Valid @PathVariable Long userId,
            @Valid @PathVariable Long productId, @Valid @RequestBody CartDto cartDto) {
        return new ResponseEntity<>(cartContentService.updateCartContent(userId, productId, cartDto), HttpStatus.OK);
    }

    // CART DELETE
    @DeleteMapping("/{userId}/cart")
    public ResponseEntity<HttpStatus> deleteCart(@Valid @PathVariable Long userId) {
        cartContentService.deleteCart(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/cart/{productId}")
    public ResponseEntity<HttpStatus> deleteCartContent(@Valid @PathVariable Long userId,
            @Valid @PathVariable Long productId) {
        cartContentService.deleteCartContent(userId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

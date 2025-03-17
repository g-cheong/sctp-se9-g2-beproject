package com.group2.theminimart.controller;

import java.util.List;

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

import com.group2.theminimart.dto.UserDto;
import com.group2.theminimart.entity.Rating;
import com.group2.theminimart.entity.User;
import com.group2.theminimart.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create

    @PostMapping
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @PostMapping("/{userId}/products/{productId}/ratings")
    public ResponseEntity<Rating> createProductRating(@PathVariable Long userId, @PathVariable Long productId,
            @RequestBody Rating rating) {
        return new ResponseEntity<>(userService.addProductRating(userId, productId, rating), HttpStatus.CREATED);
    }

    // Read

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/ratings")
    public ResponseEntity<List<Rating>> getRatings(@PathVariable Long id) {
        return new ResponseEntity<>(userService.getRatings(id), HttpStatus.OK);
    }

    @GetMapping("/{id}/products/{productId}/ratings")
    public ResponseEntity<Rating> getRatings(@PathVariable Long id, @PathVariable Long productId) {
        return new ResponseEntity<>(userService.getRatingByProductId(id, productId), HttpStatus.OK);
    }

    // Update

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
    }

    @PutMapping("/{userId}/products/{productId}/ratings")
    public ResponseEntity<Rating> updateProductRating(@PathVariable Long userId, @PathVariable Long productId,
            @RequestBody Rating rating) {
        return new ResponseEntity<>(userService.updateProductRating(userId, productId, rating), HttpStatus.OK);
    }

    // Delete

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{userId}/products/{productId}/ratings")
    public ResponseEntity<HttpStatus> deleteProductRating(@PathVariable Long userId, @PathVariable Long productId) {
        userService.deleteProductRating(userId, productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

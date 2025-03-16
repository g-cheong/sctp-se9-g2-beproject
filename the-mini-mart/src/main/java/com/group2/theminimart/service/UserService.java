package com.group2.theminimart.service;

import java.util.List;

import com.group2.theminimart.entity.Rating;
import com.group2.theminimart.entity.User;

public interface UserService {
    public User createUser(User User);

    public List<User> getUsers();

    public User getUser(Long id);

    public User updateUser(Long id, User User);

    public void deleteUser(Long id);

    public List<Rating> getRatings(Long userId);

    public Rating getRatingByProductId(Long userId, Long productId);

    public Rating addProductRating(Long userId, Long productId, Rating rating);

    public Rating updateProductRating(Long userId, Long productId, Rating rating);

    public void deleteProductRating(Long userId, Long productId);
}

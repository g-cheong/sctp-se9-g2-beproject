package com.group2.theminimart.service;

import java.util.List;

import com.group2.theminimart.dto.UserLoginRequestDto;
import com.group2.theminimart.dto.UserRegisterRequestDto;
import com.group2.theminimart.dto.UserResponseDto;
import com.group2.theminimart.dto.UserWithTokenResponseDto;
import com.group2.theminimart.entity.Rating;
import com.group2.theminimart.entity.User;

public interface UserService {
    // register
    public UserResponseDto registerUser(UserRegisterRequestDto userRegisterDto);

    // login
    public UserWithTokenResponseDto loginUser(UserLoginRequestDto userLoginRequestDto);

    // public UserResponseDto createUser(User User);

    public List<UserResponseDto> getUsers();

    public UserResponseDto getUser(Long id);

    public UserResponseDto updateUserPassword(Long id, User User);

    public void deleteUser(Long id);

    public List<Rating> getUserRatings(Long userId);

    public Rating getUserRatingByProductId(Long userId, Long productId);

    public Rating addProductRating(Long userId, Long productId, Rating rating);

    public Rating updateProductRating(Long userId, Long productId, Rating rating);

    public void deleteProductRating(Long userId, Long productId);
}

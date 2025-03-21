package com.group2.theminimart.service;

import java.nio.CharBuffer;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.group2.theminimart.config.UserAuthenticationProvider;
import com.group2.theminimart.controller.CartContentController;
import com.group2.theminimart.dto.UserLoginRequestDto;
import com.group2.theminimart.dto.UserRegisterRequestDto;
import com.group2.theminimart.dto.UserResponseDto;
import com.group2.theminimart.dto.UserWithTokenResponseDto;
import com.group2.theminimart.entity.Product;
import com.group2.theminimart.entity.Rating;
import com.group2.theminimart.entity.User;
import com.group2.theminimart.exception.ProductNotFoundException;
import com.group2.theminimart.exception.RatingAlreadyExistException;
import com.group2.theminimart.exception.RatingNotFoundException;
import com.group2.theminimart.exception.UserAlreadyExistException;
import com.group2.theminimart.exception.UserNotFoundException;
import com.group2.theminimart.exception.UserWrongLoginDetailsException;
import com.group2.theminimart.exception.WrongUserException;
import com.group2.theminimart.mapper.UserMapper;
import com.group2.theminimart.repository.CartContentRepository;
import com.group2.theminimart.repository.ProductRepository;
import com.group2.theminimart.repository.RatingRepository;
import com.group2.theminimart.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Primary
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    // TODO to implement validation

    private UserRepository userRepository;
    private RatingRepository ratingRepository;
    private ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;
    private UserAuthenticationProvider userAuthenticationProvider;

    public UserServiceImpl(UserRepository userRepository, RatingRepository ratingRepository,
            ProductRepository productRepository, CartContentController cartContentController,
            CartContentRepository cartContentRepository, CartContentServiceImpl cartContentServiceImpl,
            PasswordEncoder passwordEncoder, UserAuthenticationProvider userAuthenticationProvider) {
        this.userRepository = userRepository;
        this.ratingRepository = ratingRepository;
        this.productRepository = productRepository;
        this.passwordEncoder = passwordEncoder;
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    // @Override
    // public UserResponseDto createUser(User user) {
    // // if username exist throw UserAlreadyExistException else create user
    // if (userRepository.findByUsername(user.getUsername()).isPresent()) {
    // throw new UserAlreadyExistException();
    // }

    // return UserMapper.usertoUserResponseDto(userRepository.save(user));
    // }

    @Override
    public UserResponseDto registerUser(UserRegisterRequestDto userRegisterDto) {

        // check if username in database and throws error if does
        Optional<User> userInDb = userRepository.findByUsername(userRegisterDto.getUsername());

        if (userInDb.isPresent()) {
            throw new UserAlreadyExistException();
        }

        User user = UserMapper.userRegisterDtoToUser(userRegisterDto);

        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userRegisterDto.getPassword())));

        User savedUser = userRepository.save(user);

        return UserMapper.usertoUserResponseDto(savedUser);
    }

    @Override
    public UserWithTokenResponseDto loginUser(UserLoginRequestDto userLoginRequestDto) {
        User user = userRepository.findByUsername(userLoginRequestDto.getUsername())
                .orElseThrow(() -> new UserWrongLoginDetailsException());

        if (passwordEncoder.matches(CharBuffer.wrap(userLoginRequestDto.getPassword()), user.getPassword())) {
            UserWithTokenResponseDto userWithToken = UserMapper.userToUserWithTokenDto(user);
            userWithToken.setToken(userAuthenticationProvider.createToken(user.getUsername()));
            return userWithToken;
        }
        throw new UserWrongLoginDetailsException();
    };

    @Override
    public List<UserResponseDto> getUsers() {
        log.info("User is :" + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<User> allUser = userRepository.findAll();

        return allUser.stream().map((user) -> UserMapper.usertoUserResponseDto(user)).collect(Collectors.toList());
    }

    @Override
    public UserResponseDto getUser(Long id) {
        return UserMapper.usertoUserResponseDto(userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id)));
    }

    @Override
    public UserResponseDto updateUserPassword(Long id, User user) {
        // find user else throw UserNotFoundException
        // check username else throw WrongUserException
        // change password
        User updatedUsers = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        if (!updatedUsers.getUsername().equals(user.getUsername())) {
            throw new WrongUserException(id);
        }
        updatedUsers.setPassword(user.getPassword());
        return UserMapper.usertoUserResponseDto(userRepository.save(updatedUsers));
    }

    @Override
    public void deleteUser(Long id) {
        // check if user exist before delete or else throw error
        userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        userRepository.deleteById(id);
    }

    @Override
    public List<Rating> getUserRatings(Long userId) {
        return ratingRepository.findAllByUser_Id(userId).orElseThrow(() -> new RatingNotFoundException(userId));
    }

    @Override
    public Rating getUserRatingByProductId(Long userId, Long productId) {
        return ratingRepository.findByUser_IdAndProduct_Id(userId, productId)
                .orElseThrow(() -> new RatingNotFoundException(userId, productId));
    }

    @Override
    public Rating addProductRating(Long userId, Long productId, Rating rating) {

        // String username =
        // SecurityContextHolder.getContext().getAuthentication().getName();

        // check if userid and productid exist
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        Product existingProduct = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        // check if user id and product id pair does not exist
        // (only 1 customer review per product)
        if (ratingRepository.findByUser_IdAndProduct_Id(userId, productId).isPresent()) {
            throw new RatingAlreadyExistException(userId, productId);
        }

        rating.setUser(existingUser);
        rating.setProduct(existingProduct);

        return ratingRepository.save(rating);
    }

    @Override
    public Rating updateProductRating(Long userId, Long productId, Rating rating) {
        Rating existingRating = ratingRepository.findByUser_IdAndProduct_Id(userId, productId)
                .orElseThrow(() -> new RatingNotFoundException(userId, productId));

        existingRating.setRate(rating.getRate());

        return ratingRepository.save(existingRating);
    }

    @Override
    public void deleteProductRating(Long userId, Long productId) {
        Rating existingRating = ratingRepository.findByUser_IdAndProduct_Id(userId, productId)
                .orElseThrow(() -> new RatingNotFoundException(userId, productId));

        ratingRepository.delete(existingRating);
    }

}

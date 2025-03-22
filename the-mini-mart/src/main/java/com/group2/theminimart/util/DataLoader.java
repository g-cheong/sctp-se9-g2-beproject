package com.group2.theminimart.util;

import org.springframework.stereotype.Component;

import com.group2.theminimart.dto.UserRegisterRequestDto;
import com.group2.theminimart.entity.CartContent;
import com.group2.theminimart.entity.Product;
import com.group2.theminimart.entity.Rating;
import com.group2.theminimart.entity.User;
import com.group2.theminimart.repository.CartContentRepository;
import com.group2.theminimart.repository.ProductRepository;
import com.group2.theminimart.repository.RatingRepository;
import com.group2.theminimart.repository.UserRepository;
import com.group2.theminimart.service.UserService;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {

    private UserRepository userRepository;
    private ProductRepository productRepository;
    private RatingRepository ratingRepository;
    private CartContentRepository cartContentRepository;
    private UserService userService;

    public DataLoader(UserRepository userRepository, ProductRepository productRepository,
            RatingRepository ratingRepository, CartContentRepository cartContentRepository, UserService userService) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.ratingRepository = ratingRepository;
        this.cartContentRepository = cartContentRepository;
        this.userService = userService;
    }

    @PostConstruct
    public void loadData() {

        // CREATE USER
        userService.registerUser(UserRegisterRequestDto.builder().username("firhat").password("12345678").build());
        User firhat = userRepository.findByUsername("firhat").get();
        userService.registerUser(UserRegisterRequestDto.builder().username("min").password("12345678").build());
        User min = userRepository.findByUsername("min").get();
        userService.registerUser(UserRegisterRequestDto.builder().username("gab").password("12345678").build());
        User gab = userRepository.findByUsername("gab").get();

        // CREATE PRODUCT

        Product product1 = productRepository.save(
                Product.builder()
                        .title("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops")
                        .price(109.95)
                        .description(
                                "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday")
                        .category("men's clothing")
                        .image("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg")
                        .build());
        Product product2 = productRepository.save(
                Product.builder()
                        .title("Mens Casual Premium Slim Fit T-Shirts   ")
                        .price(22.3)
                        .description(
                                "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday")
                        .category("men's clothing")
                        .image("https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg")
                        .build());
        Product product3 = productRepository.save(
                Product.builder()
                        .title("Mens Cotton Jacket")
                        .price(55.99)
                        .description(
                                "great outerwear jackets for Spring/Autumn/Winter, suitable for many occasions, such as working, hiking, camping, mountain/rock climbing, cycling, traveling or other outdoors. Good gift choice for you or your family member. A warm hearted love to Father, husband or son in this thanksgiving or Christmas Day.")
                        .category("men's clothing")
                        .image("https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg")
                        .build());
        // CREATE RATING
        @SuppressWarnings("unused")
        Rating rating1 = ratingRepository.save(Rating.builder().rate(4.0).product(product1).user(firhat).build());
        @SuppressWarnings("unused")
        Rating rating2 = ratingRepository.save(Rating.builder().rate(3.0).product(product1).user(min).build());
        // Rating rating3 =
        // ratingRepository.save(Rating.builder().rate(5.0).product(product1).user(gab).build());

        // CREATE CART
        // CartContent cart1 =
        cartContentRepository
                .save(CartContent.builder().product(product1).user(firhat).count(1).total(109.95).build());
        cartContentRepository
                .save(CartContent.builder().product(product1).user(gab).count(1).total(109.95).build());
        cartContentRepository
                .save(CartContent.builder().product(product2).user(gab).count(1).total(109.95).build());
    }

}

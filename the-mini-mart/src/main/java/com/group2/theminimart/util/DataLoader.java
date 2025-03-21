package com.group2.theminimart.util;

import org.springframework.stereotype.Component;

import com.group2.theminimart.entity.CartContent;
import com.group2.theminimart.entity.Product;
import com.group2.theminimart.entity.Rating;
import com.group2.theminimart.entity.User;
import com.group2.theminimart.repository.CartContentRepository;
import com.group2.theminimart.repository.ProductRepository;
import com.group2.theminimart.repository.RatingRepository;
import com.group2.theminimart.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {

        private UserRepository userRepository;
        private ProductRepository productRepository;
        private RatingRepository ratingRepository;
        private CartContentRepository cartContentRepository;

        public DataLoader(UserRepository userRepository, ProductRepository productRepository,
                        RatingRepository ratingRepository, CartContentRepository cartContentRepository) {
                this.userRepository = userRepository;
                this.productRepository = productRepository;
                this.ratingRepository = ratingRepository;
                this.cartContentRepository = cartContentRepository;
        }

        @PostConstruct
        public void loadData() {

                // CREATE USER
                User firhat = userRepository.save(User.builder().username("firhat").password("12345678").build());
                User min = userRepository.save(User.builder().username("min").password("12345678").build());
                @SuppressWarnings("unused")
                User gab = userRepository.save(User.builder().username("gab").password("12345678").build());

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
                                                .title("WD 2TB Elements Portable External Hard Drive - USB 3.0")
                                                .price(64.00)
                                                .description(
                                                                "USB 3.0 and USB 2.0 Compatibility Fast data transfers Improve PC Performance High Capacity; Compatibility Formatted NTFS for Windows 10, Windows 8.1, Windows 7; Reformatting may be required for other operating systems; Compatibility may vary depending on user's hardware configuration and operating system")
                                                .category("electronics")
                                                .image("https://fakestoreapi.com/img/61IBBVJvSDL._AC_SY879_.jpg")
                                                .build());
                // CREATE RATING
                @SuppressWarnings("unused")
                Rating rating1 = ratingRepository
                                .save(Rating.builder().rate(4.0).product(product1).user(firhat).build());
                @SuppressWarnings("unused")
                Rating rating2 = ratingRepository.save(Rating.builder().rate(3.0).product(product1).user(min).build());
                // Rating rating3 =
                // ratingRepository.save(Rating.builder().rate(5.0).product(product1).user(gab).build());

                // CREATE CART
                // CartContent cart1 =
                cartContentRepository
                                .save(CartContent.builder().product(product1).user(firhat).count(1).total(109.95)
                                                .build());
                cartContentRepository
                                .save(CartContent.builder().product(product1).user(gab).count(1).total(109.95).build());
                cartContentRepository
                                .save(CartContent.builder().product(product2).user(gab).count(1).total(109.95).build());
        }

}

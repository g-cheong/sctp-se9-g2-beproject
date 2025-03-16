package com.group2.theminimart.util;

import org.springframework.stereotype.Component;

import com.group2.theminimart.entity.Product;
import com.group2.theminimart.entity.Rating;
import com.group2.theminimart.entity.User;
import com.group2.theminimart.repository.ProductRepository;
import com.group2.theminimart.repository.RatingRepository;
import com.group2.theminimart.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {

  private UserRepository userRepository;
  private ProductRepository productRepository;
  private RatingRepository ratingRepository;

  public DataLoader(UserRepository userRepository, ProductRepository productRepository,
      RatingRepository ratingRepository) {
    this.userRepository = userRepository;
    this.productRepository = productRepository;
    this.ratingRepository = ratingRepository;
  }

  @PostConstruct
  public void loadData() {
    User firhat = userRepository.save(User.builder().username("firhat").password("12345678").build());
    User min = userRepository.save(User.builder().username("min").password("12345678").build());
    User gab = userRepository.save(User.builder().username("gab").password("12345678").build());
    Product product1 = productRepository.save(
        Product.builder()
            .title("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops")
            .price(109.95)
            .description(
                "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday")
            .category("men's clothing")
            .image("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg")
            .build());
    Rating rating1 = ratingRepository.save(Rating.builder().rate(4.0).product(product1).user(firhat).build());

  }

}

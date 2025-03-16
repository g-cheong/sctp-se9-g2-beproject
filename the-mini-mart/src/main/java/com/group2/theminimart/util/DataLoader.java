package com.group2.theminimart.util;

import org.springframework.stereotype.Component;

import com.group2.theminimart.entity.Product;
import com.group2.theminimart.entity.User;
import com.group2.theminimart.repository.ProductRepository;
import com.group2.theminimart.repository.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataLoader {

  private UserRepository userRepository;
  private ProductRepository productRepository;

  public DataLoader(UserRepository userRepository, ProductRepository productRepository) {
    this.userRepository = userRepository;
    this.productRepository = productRepository;
  }

  @PostConstruct
  public void loadData() {
    userRepository.save(User.builder().username("firhat").password("12345678").build());
    userRepository.save(User.builder().username("min").password("12345678").build());
    userRepository.save(User.builder().username("gab").password("12345678").build());
    productRepository.save(
        Product.builder()
            .title("Fjallraven - Foldsack No. 1 Backpack, Fits 15 Laptops")
            .price(109.95)
            .description(
                "Your perfect pack for everyday use and walks in the forest. Stash your laptop (up to 15 inches) in the padded sleeve, your everyday")
            .category("men's clothing")
            .image("https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg")
            .build());

  }

}

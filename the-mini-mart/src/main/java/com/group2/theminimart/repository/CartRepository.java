package com.group2.theminimart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group2.theminimart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}

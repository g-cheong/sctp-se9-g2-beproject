package com.group2.theminimart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group2.theminimart.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

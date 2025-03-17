package com.group2.theminimart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.group2.theminimart.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByTitleContainingIgnoreCase(String title);

}

package com.group2.theminimart.service;

import java.util.List;

import com.group2.theminimart.entity.Product;

public interface ProductService {

    Product createProduct(Product product);

    Product getProduct(Long id);

    List<Product> getAllProducts();

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    List<Product> searchProducts(String title);
}

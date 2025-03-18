package com.group2.theminimart.service;

import java.util.List;

import com.group2.theminimart.dto.ProductDto;
import com.group2.theminimart.entity.Product;

public interface ProductService {

    ProductDto createProduct(Product product);

    ProductDto getProduct(Long id);

    List<ProductDto> getAllProducts();

    ProductDto updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    List<ProductDto> searchProducts(String title, String description, String category);
}

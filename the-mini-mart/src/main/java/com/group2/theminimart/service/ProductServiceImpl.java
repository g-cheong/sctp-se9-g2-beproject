package com.group2.theminimart.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.group2.theminimart.entity.Product;
import com.group2.theminimart.exception.ProductNotFoundException;
import com.group2.theminimart.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(Product product) {
        Product newProduct = productRepository.save(product);
        return newProduct;
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return allProducts;
    }

    @Override
    public Product updateProduct(Long id, Product product) {

        Product productToUpdate = productRepository.findById(id).get();
        // update the product retrieved from the database.
        productToUpdate.setTitle(product.getTitle());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setImage(product.getImage());
        // save the updated product back to the database.
        return productRepository.save(productToUpdate);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> searchProducts(String title) {
        List<Product> foundProducts = productRepository.findByTitleContainingIgnoreCase(title);
        return foundProducts;
    }

}

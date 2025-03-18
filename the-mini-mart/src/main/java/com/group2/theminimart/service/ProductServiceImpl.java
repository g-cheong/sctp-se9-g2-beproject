package com.group2.theminimart.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.group2.theminimart.dto.ProductDto;
import com.group2.theminimart.dto.ProductRatingDto;
import com.group2.theminimart.entity.Product;
import com.group2.theminimart.exception.ProductNotFoundException;
import com.group2.theminimart.repository.ProductRepository;
import com.group2.theminimart.repository.RatingRepository;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private RatingRepository ratingRepository;

    public ProductServiceImpl(ProductRepository productRepository, RatingRepository ratingRepository) {
        this.productRepository = productRepository;
        this.ratingRepository = ratingRepository;
    }

    @Override
    public ProductDto createProduct(Product product) {
        Product newProduct = productRepository.save(product);
        ProductDto productDto = new ProductDto();
        ProductRatingDto productRatingDto = ratingRepository.findAverageRatingByProductId(newProduct.getId());
        productDto.setId(newProduct.getId());
        productDto.setTitle(newProduct.getTitle());
        productDto.setPrice(newProduct.getPrice());
        productDto.setDescription(newProduct.getDescription());
        productDto.setCategory(newProduct.getCategory());
        productDto.setImage(newProduct.getImage());
        productDto.setRating(productRatingDto);
        return productDto;
    }

    @Override
    public ProductDto getProduct(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    ProductDto productDto = new ProductDto();
                    ProductRatingDto productRatingDto = ratingRepository.findAverageRatingByProductId(id);
                    productDto.setId(product.getId());
                    productDto.setTitle(product.getTitle());
                    productDto.setPrice(product.getPrice());
                    productDto.setDescription(product.getDescription());
                    productDto.setCategory(product.getCategory());
                    productDto.setImage(product.getImage());
                    productDto.setRating(productRatingDto);
                    return productDto;
                })
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : allProducts) {
            ProductRatingDto productRatingDto = ratingRepository.findAverageRatingByProductId(product.getId());
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setTitle(product.getTitle());
            productDto.setPrice(product.getPrice());
            productDto.setDescription(product.getDescription());
            productDto.setCategory(product.getCategory());
            productDto.setImage(product.getImage());
            productDto.setRating(productRatingDto);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    @Override
    public ProductDto updateProduct(Long id, Product product) {

        Product productToUpdate = productRepository.findById(id).get();
        // update the product retrieved from the database.
        productToUpdate.setTitle(product.getTitle());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setCategory(product.getCategory());
        productToUpdate.setImage(product.getImage());
        // save the updated product back to the database.
        Product updatedProduct = productRepository.save(productToUpdate);
        // return the updated product dto.
        ProductRatingDto productRatingDto = ratingRepository.findAverageRatingByProductId(updatedProduct.getId());
        ProductDto productDto = new ProductDto();
        productDto.setId(updatedProduct.getId());
        productDto.setTitle(updatedProduct.getTitle());
        productDto.setPrice(updatedProduct.getPrice());
        productDto.setDescription(updatedProduct.getDescription());
        productDto.setCategory(updatedProduct.getCategory());
        productDto.setImage(updatedProduct.getImage());
        productDto.setRating(productRatingDto);
        return productDto;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> searchProducts(String title, String description, String category) {
        List<Product> foundProducts = productRepository.searchProducts(title, description, category);

        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : foundProducts) {
            ProductRatingDto productRatingDto = ratingRepository.findAverageRatingByProductId(product.getId());
            ProductDto productDto = new ProductDto();
            productDto.setId(product.getId());
            productDto.setTitle(product.getTitle());
            productDto.setPrice(product.getPrice());
            productDto.setDescription(product.getDescription());
            productDto.setCategory(product.getCategory());
            productDto.setImage(product.getImage());
            productDto.setRating(productRatingDto);
            productDtos.add(productDto);
        }
        return productDtos;

    }

}

package com.group2.theminimart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group2.theminimart.dto.ProductDto;
import com.group2.theminimart.entity.Product;
import com.group2.theminimart.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody Product product) {
        ProductDto newProductDto = productService.createProduct(product);
        return new ResponseEntity<>(newProductDto, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAllallProducts() {
        List<ProductDto> allProducts = productService.getAllProducts();
        return new ResponseEntity<>(allProducts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
        ProductDto foundProduct = productService.getProduct(id);
        return new ResponseEntity<>(foundProduct, HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @Valid @RequestBody Product product) {
        ProductDto updatedProductDto = productService.updateProduct(id, product);
        return new ResponseEntity<>(updatedProductDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductDto>> searchProducts(@RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String category) {

        // Return empty list if all parameters are null or empty.
        if ((title == null || title.isEmpty()) &&
                (description == null || description.isEmpty()) &&
                (category == null || category.isEmpty())) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        title = title == null ? "" : title;
        description = description == null ? "" : description;
        category = category == null ? "" : category;

        List<ProductDto> foundProductsDto = productService.searchProducts(title, description, category);
        return new ResponseEntity<>(foundProductsDto, HttpStatus.OK);
    }
}

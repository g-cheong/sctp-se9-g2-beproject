package com.group2.theminimart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.group2.theminimart.dto.ProductDto;
import com.group2.theminimart.dto.ProductRatingDto;
import com.group2.theminimart.entity.Product;
import com.group2.theminimart.repository.ProductRepository;
import com.group2.theminimart.repository.RatingRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private RatingRepository ratingRepository;

    @InjectMocks
    ProductServiceImpl productService;

    @Test
    public void testCreateProduct() {

        // 1. SETUP
        Product product = Product.builder()
                .title("Camera 3X")
                .price(1299.99)
                .description("Latest Water Resistant Camera")
                .category("Electronics")
                .image("Camera 3X.jpg")
                .build();

        ProductRatingDto ratingDto = new ProductRatingDto(0.0, 0);

        when(productRepository.save(product)).thenReturn(product);
        when(ratingRepository.findAverageRatingByProductId(any())).thenReturn(ratingDto);

        ProductDto createdProductDto = productService.createProduct(product);
        verify(productRepository, times(1)).save(product);

        assertEquals(product.getTitle(), createdProductDto.getTitle());
        assertEquals(product.getPrice(), createdProductDto.getPrice());
        assertEquals(product.getDescription(), createdProductDto.getDescription());
        assertEquals(product.getCategory(), createdProductDto.getCategory());
        assertEquals(product.getImage(), createdProductDto.getImage());
        assertEquals(ratingDto, createdProductDto.getRating());

    }

    @Test
    public void testGetProduct() {

        // 1. SETUP
        Product product = Product.builder()
                .id(1L)
                .title("Camera 3X")
                .price(1299.99)
                .description("Latest Water Resistant Camera")
                .category("Electronics")
                .image("Camera 3X.jpg")
                .build();

        ProductRatingDto ratingDto = new ProductRatingDto(0.0, 0);

        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));
        when(ratingRepository.findAverageRatingByProductId(1L)).thenReturn(ratingDto);

        // 2. EXECUTE
        ProductDto foundProductDto = productService.getProduct(1L);

        // 3. VERIFY
        verify(productRepository, times(1)).findById(1L);

        assertEquals(product.getTitle(), foundProductDto.getTitle());
        assertEquals(product.getPrice(), foundProductDto.getPrice());
        assertEquals(product.getDescription(), foundProductDto.getDescription());
        assertEquals(product.getCategory(), foundProductDto.getCategory());
        assertEquals(product.getImage(), foundProductDto.getImage());
        assertEquals(ratingDto, foundProductDto.getRating());

    }

    @Test
    public void testGetAllProducts() {
        // 1. SETUP
        Product product1 = Product.builder()
                .id(1L)
                .title("Camera 3X")
                .price(1299.99)
                .description("Latest Water Resistant Camera")
                .category("Electronics")
                .image("Camera 3X.jpg")
                .build();

        Product product2 = Product.builder()
                .id(2L)
                .title("Camera 4X")
                .price(1499.99)
                .description("Latest Water Resistant Camera")
                .category("Electronics")
                .image("Camera 4X.jpg")
                .build();

        ProductRatingDto ratingDto1 = new ProductRatingDto(0.0, 0);
        ProductRatingDto ratingDto2 = new ProductRatingDto(0.0, 0);

        when(productRepository.findAll()).thenReturn(java.util.List.of(product1, product2));
        when(ratingRepository.findAverageRatingByProductId(1L)).thenReturn(ratingDto1);
        when(ratingRepository.findAverageRatingByProductId(2L)).thenReturn(ratingDto2);

        // 2. EXECUTE
        var productDtos = productService.getAllProducts();

        // 3. VERIFY
        verify(productRepository, times(1)).findAll();
        assertEquals(2, productDtos.size());

        assertEquals(product1.getTitle(), productDtos.get(0).getTitle());
        assertEquals(product1.getPrice(), productDtos.get(0).getPrice());
        assertEquals(product1.getDescription(), productDtos.get(0).getDescription());
        assertEquals(product1.getCategory(), productDtos.get(0).getCategory());
        assertEquals(product1.getImage(), productDtos.get(0).getImage());
        assertEquals(ratingDto1, productDtos.get(0).getRating());

        assertEquals(product2.getTitle(), productDtos.get(1).getTitle());
        assertEquals(product2.getPrice(), productDtos.get(1).getPrice());
        assertEquals(product2.getDescription(), productDtos.get(1).getDescription());
        assertEquals(product2.getCategory(), productDtos.get(1).getCategory());
        assertEquals(product2.getImage(), productDtos.get(1).getImage());
        assertEquals(ratingDto2, productDtos.get(1).getRating());
    }

    @Test
    public void testUpdateProduct() {
        // 1. SETUP
        Product product = Product.builder()
                .id(1L)
                .title("Camera 3X")
                .price(1299.99)
                .description("Latest Water Resistant Camera")
                .category("Electronics")
                .image("Camera 3X.jpg")
                .build();

        ProductRatingDto ratingDto = new ProductRatingDto(0.0, 0);

        when(productRepository.findById(1L)).thenReturn(java.util.Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);
        when(ratingRepository.findAverageRatingByProductId(1L)).thenReturn(ratingDto);

        // 2. EXECUTE
        ProductDto updatedProductDto = productService.updateProduct(1L, product);

        // 3. VERIFY
        verify(productRepository, times(1)).findById(1L);
        verify(productRepository, times(1)).save(product);

        assertEquals(product.getTitle(), updatedProductDto.getTitle());
        assertEquals(product.getPrice(), updatedProductDto.getPrice());
        assertEquals(product.getDescription(), updatedProductDto.getDescription());
        assertEquals(product.getCategory(), updatedProductDto.getCategory());
        assertEquals(product.getImage(), updatedProductDto.getImage());
        assertEquals(ratingDto, updatedProductDto.getRating());
    }

    @Test
    public void testDeleteProduct() {
        // 1. SETUP
        Long id = 1L;

        // 2. EXECUTE
        productService.deleteProduct(id);

        // 3. VERIFY
        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    public void searchProducts() {
        // 1. SETUP
        Product product1 = Product.builder()
                .id(1L)
                .title("Camera 3X")
                .price(1299.99)
                .description("Latest Water Resistant Camera")
                .category("Electronics")
                .image("Camera 3X.jpg")
                .build();

        Product product2 = Product.builder()
                .id(2L)
                .title("Camera 4X")
                .price(1499.99)
                .description("Latest Water Resistant Camera")
                .category("Electronics")
                .image("Camera 4X.jpg")
                .build();

        ProductRatingDto ratingDto1 = new ProductRatingDto(0.0, 0);
        ProductRatingDto ratingDto2 = new ProductRatingDto(0.0, 0);

        when(productRepository.searchProducts("Camera", "", "Electronics"))
                .thenReturn(java.util.List.of(product1, product2));

        when(ratingRepository.findAverageRatingByProductId(1L)).thenReturn(ratingDto1);
        when(ratingRepository.findAverageRatingByProductId(2L)).thenReturn(ratingDto2);

        // 2. EXECUTE
        var productDtos = productService.searchProducts("Camera", "", "Electronics");

        // 3. VERIFY
        verify(productRepository, times(1)).searchProducts("Camera", "", "Electronics");
        assertEquals(2, productDtos.size());

        assertEquals(product1.getTitle(), productDtos.get(0).getTitle());
        assertEquals(product1.getPrice(), productDtos.get(0).getPrice());
        assertEquals(product1.getDescription(), productDtos.get(0).getDescription());
        assertEquals(product1.getCategory(), productDtos.get(0).getCategory());
        assertEquals(product1.getImage(), productDtos.get(0).getImage());
        assertEquals(ratingDto1, productDtos.get(0).getRating());

        assertEquals(product2.getTitle(), productDtos.get(1).getTitle());
        assertEquals(product2.getPrice(), productDtos.get(1).getPrice());
        assertEquals(product2.getDescription(), productDtos.get(1).getDescription());
        assertEquals(product2.getCategory(), productDtos.get(1).getCategory());
        assertEquals(product2.getImage(), productDtos.get(1).getImage());
        assertEquals(ratingDto2, productDtos.get(1).getRating());

    }
}

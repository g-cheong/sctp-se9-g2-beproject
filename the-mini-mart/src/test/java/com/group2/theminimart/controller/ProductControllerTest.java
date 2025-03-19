package com.group2.theminimart.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.group2.theminimart.entity.Product;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        public void getProductByIdTest() throws Exception {
                // Step 1: Build a GET request to /products/1
                RequestBuilder request = MockMvcRequestBuilders.get("/products/1");

                // Step 2: Perform the request, get the response and assert
                mockMvc.perform(request)
                                // Assert that the status code is OK 200
                                .andExpect(status().isOk())
                                // Assert that the content type is JSON
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                // Assert that id returned is 1
                                .andExpect(jsonPath("$.id").value(1));

        }

        @Test
        public void getAllProductsTest() throws Exception {
                // Step 1: Build a GET request /products
                RequestBuilder request = MockMvcRequestBuilders.get("/products");

                // Step 2: Perform the request (execute), get the response and assert
                mockMvc.perform(request)
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.size()").value(2));

        }

        @Test
        public void validCreateProductTest() throws Exception {
                // Step 1: Build a POST request to /products
                Product newProduct = Product.builder().title("Product 3").price(100.0).description("Description 3")
                                .category("Category 3").image("Image 3").build();

                String newProductAsJSON = objectMapper.writeValueAsString(newProduct);

                RequestBuilder request = MockMvcRequestBuilders.post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newProductAsJSON);

                // Step 2: Perform the request, get the response and assert
                mockMvc.perform(request)
                                .andExpect(status().isCreated())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(3))
                                .andExpect(jsonPath("$.title").value("Product 3"))
                                .andExpect(jsonPath("$.price").value(100.0))
                                .andExpect(jsonPath("$.description").value("Description 3"))
                                .andExpect(jsonPath("$.category").value("Category 3"))
                                .andExpect(jsonPath("$.image").value("Image 3"))
                                .andExpect(jsonPath("$.rating.rate").exists())
                                .andExpect(jsonPath("$.rating.count").exists());

        }

        @Test
        public void invalidCreateProductTest() throws Exception {
                Product newProduct = Product.builder().title("").price(0.00).description("Description 3")
                                .category("Category 3").image("Image 3").build();

                String newProductAsJSON = objectMapper.writeValueAsString(newProduct);

                RequestBuilder request = MockMvcRequestBuilders.post("/products")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(newProductAsJSON);

                mockMvc.perform(request)
                                .andExpect(status().isBadRequest())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        }

        @Test
        public void validUpdateProductTest() throws Exception {
                // Step 1: Build a PUT request to /products/1
                Product updatedProduct = Product.builder().title("Product 1 Updated").price(200.0)
                                .description("Description 1 Updated").category("Category 1 Updated")
                                .image("Image 1 Updated").build();

                String updatedProductAsJSON = objectMapper.writeValueAsString(updatedProduct);

                RequestBuilder request = MockMvcRequestBuilders.put("/products/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updatedProductAsJSON);

                // Step 2: Perform the request, get the response and assert
                mockMvc.perform(request)
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.id").value(1))
                                .andExpect(jsonPath("$.title").value("Product 1 Updated"))
                                .andExpect(jsonPath("$.price").value(200.0))
                                .andExpect(jsonPath("$.description").value("Description 1 Updated"))
                                .andExpect(jsonPath("$.category").value("Category 1 Updated"))
                                .andExpect(jsonPath("$.image").value("Image 1 Updated"))
                                .andExpect(jsonPath("$.rating.rate").exists())
                                .andExpect(jsonPath("$.rating.count").exists());

        }

        @Test
        public void invalidUpdateProductTest() throws Exception {
                Product updatedProduct = Product.builder().title("").price(0.00).description("Description 1 Updated")
                                .category("Category 1 Updated").image("Image 1 Updated").build();

                String updatedProductAsJSON = objectMapper.writeValueAsString(updatedProduct);

                RequestBuilder request = MockMvcRequestBuilders.put("/products/1")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updatedProductAsJSON);

                mockMvc.perform(request)
                                .andExpect(status().isBadRequest())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        }

        @Test
        public void deleteProductTest() throws Exception {
                // Step 1: Build a DELETE request to /products/1
                RequestBuilder request = MockMvcRequestBuilders.delete("/products/3");

                // Step 2: Perform the request, get the response and assert
                mockMvc.perform(request)
                                .andExpect(status().isNoContent());

        }

        @Test
        public void searchProductsTest() throws Exception {
                // Step 1: Build a GET request to /products/search?title=Product 1
                RequestBuilder request = MockMvcRequestBuilders
                                .get("/products/search?title=product 1&description=Description 1&category=category 1");

                // Step 2: Perform the request, get the response and assert
                mockMvc.perform(request)
                                .andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.size()").value(1))
                                .andExpect(jsonPath("$[0].id").value(1))
                                .andExpect(jsonPath("$[0].title").value("Product 1 Updated"))
                                .andExpect(jsonPath("$[0].price").exists())
                                .andExpect(jsonPath("$[0].description").exists())
                                .andExpect(jsonPath("$[0].category").exists())
                                .andExpect(jsonPath("$[0].image").exists())
                                .andExpect(jsonPath("$[0].rating.rate").exists())
                                .andExpect(jsonPath("$[0].rating.count").exists());

        }
}

package com.group2.theminimart.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    // private Long id;
    // private Long userId;
    // private Long productId;
    // private double price;
    // @Positive(message = "Count must be a positive number")
    // private int count;
    // @Positive(message = "Total must be a positive number")
    // private double total;

    @Positive
    private Long id;
    private String title;
    @Positive
    private double price;
    private String description;
    private String image;
    @Positive
    private int quantity;
    @Positive
    private double total;
}

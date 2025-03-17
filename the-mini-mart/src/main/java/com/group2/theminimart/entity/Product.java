package com.group2.theminimart.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "products")
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column
  @NotBlank(message = "Product title is required.")
  private String title;

  @Column
  @NotNull(message = "Product price is required.")
  @Positive(message = "Product price must be greater than 0.")
  private Double price;

  @Column
  private String description;

  @Column
  private String category;

  @Column
  private String image;

  @JsonIgnoreProperties("product")
  @OneToMany(mappedBy = "product")
  private List<Rating> ratings;
  @JsonIgnoreProperties("product")
  @OneToMany(mappedBy = "product")
  private List<CartContent> cart;
}

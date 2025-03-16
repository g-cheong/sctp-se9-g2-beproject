package com.group2.theminimart.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
  private String title;

  @Column
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

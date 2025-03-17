package com.group2.theminimart.dto;

import java.util.List;

import com.group2.theminimart.entity.CartContent;
// import com.group2.theminimart.entity.Rating;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
  // TODO check of rating is needed
  private Long id;
  private String username;
  // private List<Rating> ratings;
  private List<CartContent> cart;
}

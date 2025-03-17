package com.group2.theminimart.mapper;

import com.group2.theminimart.dto.UserDto;
import com.group2.theminimart.entity.User;

public class UserMapper {
  public static UserDto UsertoDto(User user) {
    return new UserDto(
        user.getId(),
        user.getUsername());
    // user.getRatings(),
    // user.getCart());
  }
}

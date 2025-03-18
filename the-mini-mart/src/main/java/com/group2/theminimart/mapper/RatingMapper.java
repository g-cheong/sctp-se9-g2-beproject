package com.group2.theminimart.mapper;

import com.group2.theminimart.dto.RatingDto;
import com.group2.theminimart.entity.Rating;

public class RatingMapper {
  public static RatingDto RatingtoDto(Rating rating) {
    return new RatingDto(rating.getId(), rating.getRate(), rating.getUser().getId(), rating.getProduct().getId());

  }
}

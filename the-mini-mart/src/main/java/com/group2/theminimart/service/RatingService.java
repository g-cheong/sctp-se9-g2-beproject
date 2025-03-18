package com.group2.theminimart.service;

import java.util.List;

import com.group2.theminimart.dto.RatingDto;
import com.group2.theminimart.entity.Rating;

public interface RatingService {
  public List<RatingDto> getRatings();

  public RatingDto getRating(Long id);

  public RatingDto updateRating(Long id, Rating rating);

  public void deleteRating(Long id);
}

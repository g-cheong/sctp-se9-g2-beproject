package com.group2.theminimart.service;

import java.util.List;

import com.group2.theminimart.entity.Rating;

public interface RatingService {
  public List<Rating> getRatings();

  public Rating getRating(Long id);

  public Rating updateRating(Long id, Rating rating);

  public void deleteRating(Long id);
}

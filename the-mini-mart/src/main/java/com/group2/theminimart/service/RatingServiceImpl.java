package com.group2.theminimart.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.group2.theminimart.entity.Rating;
import com.group2.theminimart.exception.RatingNotFoundException;
import com.group2.theminimart.repository.RatingRepository;

@Primary
@Service
public class RatingServiceImpl implements RatingService {

  private RatingRepository ratingRepository;

  public RatingServiceImpl(RatingRepository ratingRepository) {
    this.ratingRepository = ratingRepository;
  }

  @Override
  public List<Rating> getRatings() {
    return ratingRepository.findAll();
  }

  @Override
  public Rating getRating(Long id) {
    return ratingRepository.findById(id).orElseThrow(() -> new RatingNotFoundException());
  }

  @Override
  public Rating updateRating(Long id, Rating rating) {
    Rating updatedRating = ratingRepository.findById(id).orElseThrow(() -> new RatingNotFoundException());
    updatedRating.setRate(rating.getRate());

    return ratingRepository.save(updatedRating);

  }

  @Override
  public void deleteRating(Long id) {
    Rating existingRating = ratingRepository.findById(id).orElseThrow(() -> new RatingNotFoundException());

    ratingRepository.delete(existingRating);

  }

}

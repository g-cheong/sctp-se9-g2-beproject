package com.group2.theminimart.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group2.theminimart.dto.RatingDto;
import com.group2.theminimart.entity.Rating;
import com.group2.theminimart.service.RatingService;

@RestController
@RequestMapping("/ratings")
public class RatingController {

  private RatingService ratingService;

  public RatingController(RatingService ratingService) {
    this.ratingService = ratingService;
  }

  // Create (only user able to create)

  // Read
  @GetMapping
  public ResponseEntity<List<RatingDto>> getRatings() {
    return new ResponseEntity<>(ratingService.getRatings(), HttpStatus.OK);
  }

  // Update
  @PutMapping("/{id}")
  public ResponseEntity<RatingDto> updateRating(@PathVariable Long id, @RequestBody Rating rating) {
    return new ResponseEntity<>(ratingService.updateRating(id, rating), HttpStatus.OK);
  }

  // Delete
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteRating(@PathVariable Long id) {
    ratingService.deleteRating(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}

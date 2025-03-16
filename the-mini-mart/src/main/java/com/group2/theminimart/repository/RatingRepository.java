package com.group2.theminimart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group2.theminimart.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {

  Optional<Rating> findByUser_IdAndProduct_Id(Long userId, Long productId);

  Optional<List<Rating>> findAllByUser_Id(Long userId);
}

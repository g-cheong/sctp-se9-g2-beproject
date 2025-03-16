package com.group2.theminimart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group2.theminimart.entity.Rating;

public interface RatingRepository extends JpaRepository<Rating, Long> {

}

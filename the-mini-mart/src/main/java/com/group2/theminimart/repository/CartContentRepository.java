package com.group2.theminimart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group2.theminimart.entity.CartContent;

public interface CartContentRepository extends JpaRepository<CartContent, Long> {

}

package com.group2.theminimart.exception;

public class RatingAlreadyExistException extends RuntimeException {

  public RatingAlreadyExistException(Long userId, Long productId) {
    super("Rating for user id '" + userId + "' and productId '" + productId + "' already exist.");
  }

}

package com.group2.theminimart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group2.theminimart.entity.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsernameIgnoringCase(String username);
}

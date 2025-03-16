package com.group2.theminimart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group2.theminimart.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}

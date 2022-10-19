package com.microservices.udemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.udemy.entity.User;

public interface UserJpaRepository extends JpaRepository<User, Integer> {

}

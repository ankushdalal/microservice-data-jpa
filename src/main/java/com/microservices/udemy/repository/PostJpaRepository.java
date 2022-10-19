package com.microservices.udemy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.udemy.entity.Post;

public interface PostJpaRepository extends JpaRepository<Post, Integer> {

}

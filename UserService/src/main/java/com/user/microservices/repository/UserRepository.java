package com.user.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.microservices.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}

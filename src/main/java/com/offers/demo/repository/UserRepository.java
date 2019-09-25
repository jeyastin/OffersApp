package com.offers.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.offers.demo.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}

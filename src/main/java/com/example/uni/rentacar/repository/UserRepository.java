package com.example.uni.rentacar.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.uni.rentacar.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}

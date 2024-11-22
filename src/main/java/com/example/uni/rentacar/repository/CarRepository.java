package com.example.uni.rentacar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.uni.rentacar.model.Car;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {
    List<Car> findByLocationAndIsDeletedFalse(String location);

    Optional<Car> findByIdAndIsDeletedFalse(Long id);
}

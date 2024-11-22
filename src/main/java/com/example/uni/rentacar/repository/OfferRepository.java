package com.example.uni.rentacar.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.uni.rentacar.model.Offer;

@Repository
public interface OfferRepository extends CrudRepository<Offer, Long> {
    Iterable<Offer> findAllByUserIdAndIsDeletedFalse(Long userId);

    Iterable<Offer> findAllByIsDeletedFalse();
}

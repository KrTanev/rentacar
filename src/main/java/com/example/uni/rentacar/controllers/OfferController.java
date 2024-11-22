package com.example.uni.rentacar.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.uni.rentacar.model.Offer;
import com.example.uni.rentacar.services.OfferService;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer) {
        Long userId = offer.getUserId();
        Long carId = offer.getCarId();
        int rentalDays = offer.getRentalDays();

        Offer createdOffer = offerService.createOffer(userId, carId, rentalDays);

        return new ResponseEntity<>(createdOffer, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Offer>> getAllOffers() {
        return ResponseEntity.ok(offerService.getAllOffers());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Iterable<Offer>> getOffersByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(offerService.getOffersByUserId(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Long id) {
        return ResponseEntity.ok(offerService.getOfferById(id));
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<Offer> acceptOffer(@PathVariable Long id) {
        Offer offer = offerService.acceptOffer(id);
        return ResponseEntity.ok(offer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }
}
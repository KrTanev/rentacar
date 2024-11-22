package com.example.uni.rentacar.services;

import org.springframework.stereotype.Service;

import com.example.uni.rentacar.model.Offer;
import com.example.uni.rentacar.repository.CarRepository;
import com.example.uni.rentacar.repository.OfferRepository;
import com.example.uni.rentacar.repository.UserRepository;

@Service
public class OfferService {
    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public OfferService(OfferRepository offerRepository, UserRepository userRepository, CarRepository carRepository) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    public Offer createOffer(Long userId, Long carId, int rentalDays) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        var car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Car not found"));

        double basePrice = car.getPricePerDay() * rentalDays;
        double weekendSurcharge = 0.1 * basePrice;
        double accidentFee = user.isHasAccidents() ? 200 : 0;

        double totalPrice = basePrice + weekendSurcharge + accidentFee;

        Offer offer = new Offer();
        offer.setUserId(userId);
        offer.setCarId(carId);
        offer.setRentalDays(rentalDays);
        offer.setTotalPrice(totalPrice);
        offer.setAccepted(false);
        offer.setDeleted(false);

        return offerRepository.save(offer);
    }

    public Iterable<Offer> getAllOffers() {
        return offerRepository.findAllByIsDeletedFalse();
    }

    public Iterable<Offer> getOffersByUserId(Long userId) {
        return offerRepository.findAllByUserIdAndIsDeletedFalse(userId);
    }

    public Offer getOfferById(Long id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Offer not found"));
    }

    public Offer acceptOffer(Long id) {
        Offer offer = getOfferById(id);
        offer.setAccepted(true);
        return offerRepository.save(offer);
    }

    public void deleteOffer(Long id) {
        Offer offer = getOfferById(id);
        offer.setDeleted(true);
        offerRepository.save(offer);
    }
}

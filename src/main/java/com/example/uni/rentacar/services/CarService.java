package com.example.uni.rentacar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.uni.rentacar.model.Car;
import com.example.uni.rentacar.repository.CarRepository;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    private static final List<String> ALLOWED_LOCATIONS = List.of("Sofia", "Plovdiv", "Varna", "Burgas");

    public List<Car> getCarsByLocation(String location) {
        return carRepository.findByLocationAndIsDeletedFalse(location);
    }

    public Car getCarById(Long id) {
        return carRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
    }

    public Car addCar(Car car) {
        validateLocation(car.getLocation());
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car updatedCar) {
        Car car = getCarById(id);
        validateLocation(updatedCar.getLocation());
        car.setBrand(updatedCar.getBrand());
        car.setModel(updatedCar.getModel());
        car.setLocation(updatedCar.getLocation());
        car.setPricePerDay(updatedCar.getPricePerDay());
        return carRepository.save(car);
    }

    public void deleteCar(Long id) {
        Car car = getCarById(id);
        car.setDeleted(true);
        carRepository.save(car);
    }

    private void validateLocation(String location) {
        if (!ALLOWED_LOCATIONS.contains(location)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Invalid location. Allowed locations: " + ALLOWED_LOCATIONS);
        }
    }
}

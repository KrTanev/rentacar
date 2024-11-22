package com.example.uni.rentacar.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.uni.rentacar.model.Car;
import com.example.uni.rentacar.services.CarService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping
    public List<Car> getCarsByLocation(@RequestParam String location) {
        return carService.getCarsByLocation(location);
    }

    @PostMapping
    public Car addCar(@Valid @RequestBody Car car) {
        return carService.addCar(car);
    }

    @PutMapping("/{id}")
    public Car updateCar(@PathVariable Long id, @Valid @RequestBody Car car) {
        return carService.updateCar(id, car);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
    }
}

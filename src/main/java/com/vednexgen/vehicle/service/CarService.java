package com.vednexgen.vehicle.service;

import com.vednexgen.vehicle.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {

    List<Car> cars = new ArrayList<>();

    public CarService() {
        cars.add(new Car("Maruti", "Desire", "Red"));
        cars.add(new Car("Kia", "Sonet", "Black"));
    }

    public List<Car> gatCars() {
        return cars;
    }
}
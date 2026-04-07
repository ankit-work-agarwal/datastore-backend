package com.personal.datastore.service;

import com.personal.datastore.model.Vehicle;
import com.personal.datastore.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    public Vehicle save(Vehicle vehicle) {
        return repository.save(vehicle);
    }

    public List<Vehicle> getAll() {
        return repository.findAll();
    }
}
package com.personal.datastore.controller;

import com.personal.datastore.model.Vehicle;
import com.personal.datastore.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @PostMapping
    public Vehicle add(@RequestBody Vehicle vehicle) {
        return service.save(vehicle);
    }

    @GetMapping
    public List<Vehicle> getAll() {
        return service.getAll();
    }
}
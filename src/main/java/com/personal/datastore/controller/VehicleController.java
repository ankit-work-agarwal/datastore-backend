package com.personal.datastore.controller;

import com.personal.datastore.dto.VehicleDTO;
import com.personal.datastore.model.Vehicle;
import com.personal.datastore.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @PostMapping
    public VehicleDTO add(@RequestBody Vehicle vehicle) {
        return service.save(vehicle);
    }

    @GetMapping
    public List<VehicleDTO> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Vehicle with id " + id + " deleted successfully");
    }
}


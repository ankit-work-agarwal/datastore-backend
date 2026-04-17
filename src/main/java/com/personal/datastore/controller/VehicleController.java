package com.personal.datastore.controller;

import com.personal.datastore.dto.VehicleDTO;
import com.personal.datastore.model.Vehicle;
import com.personal.datastore.service.VehicleService;
import jakarta.validation.Valid;
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
    public VehicleDTO add(@Valid @RequestBody Vehicle vehicle) {
        return service.save(vehicle);
    }

    @GetMapping
    public List<VehicleDTO> getAll(@RequestParam(required = false) Long familyMemberId) {
        if (familyMemberId != null) {
            return service.getByFamilyMemberId(familyMemberId);
        }
        return service.getAll();
    }

    @GetMapping("/{id}")
    public VehicleDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public VehicleDTO update(@PathVariable Long id, @Valid @RequestBody Vehicle vehicle) {
        return service.update(id, vehicle);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Vehicle with id " + id + " deleted successfully");
    }
}

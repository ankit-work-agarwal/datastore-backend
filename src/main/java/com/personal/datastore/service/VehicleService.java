package com.personal.datastore.service;

import com.personal.datastore.dto.VehicleDTO;
import com.personal.datastore.model.Vehicle;
import com.personal.datastore.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository repository;

    public VehicleDTO save(Vehicle vehicle) {
        return mapToDTO(repository.save(vehicle));
    }

    public List<VehicleDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
        repository.deleteById(id);
    }

    private VehicleDTO mapToDTO(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setType(vehicle.getType());
        dto.setModel(vehicle.getModel());
        dto.setRegistrationNumber(vehicle.getRegistrationNumber());
        return dto;
    }
}


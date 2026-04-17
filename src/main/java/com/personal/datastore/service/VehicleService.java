package com.personal.datastore.service;

import com.personal.datastore.dto.VehicleDTO;
import com.personal.datastore.model.Vehicle;
import com.personal.datastore.exception.ResourceNotFoundException;
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

    public List<VehicleDTO> getByFamilyMemberId(Long familyMemberId) {
        return repository.findByOwner_Id(familyMemberId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public VehicleDTO getById(Long id) {
        Vehicle vehicle = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));
        return mapToDTO(vehicle);
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));
        repository.deleteById(id);
    }

    public VehicleDTO update(Long id, Vehicle updated) {
        Vehicle existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vehicle not found with id: " + id));
        existing.setType(updated.getType());
        existing.setModel(updated.getModel());
        existing.setRegistrationNumber(updated.getRegistrationNumber());
        return mapToDTO(repository.save(existing));
    }

    private VehicleDTO mapToDTO(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setType(vehicle.getType());
        dto.setModel(vehicle.getModel());
        dto.setRegistrationNumber(vehicle.getRegistrationNumber());
        if (vehicle.getOwner() != null) {
            dto.setOwnerName(vehicle.getOwner().getName());
        }
        return dto;
    }
}

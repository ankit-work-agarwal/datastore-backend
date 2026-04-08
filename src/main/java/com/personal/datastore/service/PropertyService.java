package com.personal.datastore.service;

import com.personal.datastore.dto.PropertyDTO;
import com.personal.datastore.exception.ResourceNotFoundException;
import com.personal.datastore.model.FamilyMember;
import com.personal.datastore.model.Property;
import com.personal.datastore.repository.FamilyRepository;
import com.personal.datastore.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository repository;

    @Autowired
    private FamilyRepository familyRepository;

    public PropertyDTO save(Property property) {
        if (property.getOwner() != null && property.getOwner().getId() != null) {
            FamilyMember owner = familyRepository
                    .findById(property.getOwner().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Family member not found with id: " + property.getOwner().getId()));
            property.setOwner(owner);
        }
        return mapToDTO(repository.save(property));
    }

    public List<PropertyDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));
        repository.deleteById(id);
    }

    private PropertyDTO mapToDTO(Property property) {
        PropertyDTO dto = new PropertyDTO();
        dto.setId(property.getId());
        dto.setType(property.getType());
        dto.setAddress(property.getAddress());
        dto.setCity(property.getCity());
        dto.setState(property.getState());
        dto.setPurchaseDate(property.getPurchaseDate());
        dto.setPurchaseValue(property.getPurchaseValue());
        dto.setCurrentValue(property.getCurrentValue());
        dto.setIsRented(property.getIsRented());
        dto.setRentalIncome(property.getRentalIncome());
        return dto;
    }
}


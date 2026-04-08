package com.personal.datastore.service;

import com.personal.datastore.dto.DocumentDTO;
import com.personal.datastore.dto.FamilyMemberDTO;
import com.personal.datastore.dto.VehicleDTO;
import com.personal.datastore.model.Document;
import com.personal.datastore.model.FamilyMember;
import com.personal.datastore.model.Vehicle;
import com.personal.datastore.exception.ResourceNotFoundException;
import com.personal.datastore.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyService {

    @Autowired
    private FamilyRepository repository;

    public FamilyMember addMember(FamilyMember member) {
        return repository.save(member);
    }

    public List<FamilyMemberDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Family member not found with id: " + id));
        repository.deleteById(id);
    }

    private FamilyMemberDTO mapToDTO(FamilyMember member) {

        FamilyMemberDTO dto = new FamilyMemberDTO();
        dto.setId(member.getId());
        dto.setName(member.getName());
        dto.setRelation(member.getRelation());
        dto.setPhone(member.getPhone());
        dto.setEmail(member.getEmail());

        if (member.getVehicles() != null) {
            List<VehicleDTO> vehicles = member.getVehicles()
                    .stream()
                    .map(this::mapVehicle)
                    .toList();
            dto.setVehicles(vehicles);
        }

        if (member.getDocuments() != null) {
            List<DocumentDTO> docs = member.getDocuments()
                    .stream()
                    .map(this::mapDocument)
                    .toList();
            dto.setDocuments(docs);
        }

        return dto;
    }

    private VehicleDTO mapVehicle(Vehicle vehicle) {
        VehicleDTO dto = new VehicleDTO();
        dto.setId(vehicle.getId());
        dto.setType(vehicle.getType());
        dto.setModel(vehicle.getModel());
        dto.setRegistrationNumber(vehicle.getRegistrationNumber());
        return dto;
    }

    private DocumentDTO mapDocument(Document doc) {
        DocumentDTO dto = new DocumentDTO();
        dto.setId(doc.getId());
        dto.setTitle(doc.getTitle());
        dto.setType(doc.getType());
        dto.setFilePath(doc.getFilePath());
        dto.setExpiryDate(doc.getExpiryDate());

        return dto;
    }
}
package com.personal.datastore.service;

import com.personal.datastore.dto.InsuranceDTO;
import com.personal.datastore.exception.ResourceNotFoundException;
import com.personal.datastore.model.FamilyMember;
import com.personal.datastore.model.Insurance;
import com.personal.datastore.repository.FamilyRepository;
import com.personal.datastore.repository.InsuranceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceService {

    @Autowired
    private InsuranceRepository repository;

    @Autowired
    private FamilyRepository familyRepository;

    public InsuranceDTO save(Insurance insurance) {
        if (insurance.getHolder() != null && insurance.getHolder().getId() != null) {
            FamilyMember holder = familyRepository
                    .findById(insurance.getHolder().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Family member not found with id: " + insurance.getHolder().getId()));
            insurance.setHolder(holder);
        }
        return mapToDTO(repository.save(insurance));
    }

    public List<InsuranceDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Insurance not found with id: " + id));
        repository.deleteById(id);
    }

    private InsuranceDTO mapToDTO(Insurance insurance) {
        InsuranceDTO dto = new InsuranceDTO();
        dto.setId(insurance.getId());
        dto.setType(insurance.getType());
        dto.setPolicyNumber(insurance.getPolicyNumber());
        dto.setProvider(insurance.getProvider());
        dto.setPremiumAmount(insurance.getPremiumAmount());
        dto.setSumAssured(insurance.getSumAssured());
        dto.setStartDate(insurance.getStartDate());
        dto.setExpiryDate(insurance.getExpiryDate());
        return dto;
    }
}


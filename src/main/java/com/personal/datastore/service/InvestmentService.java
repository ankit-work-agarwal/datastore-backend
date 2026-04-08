package com.personal.datastore.service;

import com.personal.datastore.dto.InvestmentDTO;
import com.personal.datastore.exception.ResourceNotFoundException;
import com.personal.datastore.model.FamilyMember;
import com.personal.datastore.model.Investment;
import com.personal.datastore.repository.FamilyRepository;
import com.personal.datastore.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvestmentService {

    @Autowired
    private InvestmentRepository repository;

    @Autowired
    private FamilyRepository familyRepository;

    public InvestmentDTO save(Investment investment) {
        if (investment.getHolder() != null && investment.getHolder().getId() != null) {
            FamilyMember holder = familyRepository
                    .findById(investment.getHolder().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Family member not found with id: " + investment.getHolder().getId()));
            investment.setHolder(holder);
        }
        return mapToDTO(repository.save(investment));
    }

    public List<InvestmentDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Investment not found with id: " + id));
        repository.deleteById(id);
    }

    private InvestmentDTO mapToDTO(Investment investment) {
        InvestmentDTO dto = new InvestmentDTO();
        dto.setId(investment.getId());
        dto.setType(investment.getType());
        dto.setName(investment.getName());
        dto.setInstitution(investment.getInstitution());
        dto.setInvestedAmount(investment.getInvestedAmount());
        dto.setCurrentValue(investment.getCurrentValue());
        dto.setStartDate(investment.getStartDate());
        dto.setMaturityDate(investment.getMaturityDate());
        return dto;
    }
}


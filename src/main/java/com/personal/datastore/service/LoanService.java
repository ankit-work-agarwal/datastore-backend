package com.personal.datastore.service;

import com.personal.datastore.dto.LoanDTO;
import com.personal.datastore.exception.ResourceNotFoundException;
import com.personal.datastore.model.FamilyMember;
import com.personal.datastore.model.Loan;
import com.personal.datastore.repository.FamilyRepository;
import com.personal.datastore.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    @Autowired
    private LoanRepository repository;

    @Autowired
    private FamilyRepository familyRepository;

    public LoanDTO save(Loan loan) {
        resolveBorrower(loan);
        return mapToDTO(repository.save(loan));
    }

    public List<LoanDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public List<LoanDTO> getByFamilyMemberId(Long familyMemberId) {
        return repository.findByBorrower_Id(familyMemberId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public LoanDTO getById(Long id) {
        Loan loan = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id: " + id));
        return mapToDTO(loan);
    }

    public LoanDTO update(Long id, Loan updated) {
        Loan existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id: " + id));
        existing.setLoanType(updated.getLoanType());
        existing.setBankName(updated.getBankName());
        existing.setAccountNumber(updated.getAccountNumber());
        existing.setPrincipalAmount(updated.getPrincipalAmount());
        existing.setOutstandingAmount(updated.getOutstandingAmount());
        existing.setInterestRate(updated.getInterestRate());
        existing.setEmiAmount(updated.getEmiAmount());
        existing.setStartDate(updated.getStartDate());
        existing.setEndDate(updated.getEndDate());
        existing.setStatus(updated.getStatus());
        return mapToDTO(repository.save(existing));
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id: " + id));
        repository.deleteById(id);
    }

    private void resolveBorrower(Loan loan) {
        if (loan.getBorrower() != null && loan.getBorrower().getId() != null) {
            FamilyMember borrower = familyRepository
                    .findById(loan.getBorrower().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Family member not found with id: " + loan.getBorrower().getId()));
            loan.setBorrower(borrower);
        }
    }

    private LoanDTO mapToDTO(Loan loan) {
        LoanDTO dto = new LoanDTO();
        dto.setId(loan.getId());
        dto.setLoanType(loan.getLoanType());
        dto.setBankName(loan.getBankName());
        dto.setAccountNumber(loan.getAccountNumber());
        dto.setPrincipalAmount(loan.getPrincipalAmount());
        dto.setOutstandingAmount(loan.getOutstandingAmount());
        dto.setInterestRate(loan.getInterestRate());
        dto.setEmiAmount(loan.getEmiAmount());
        dto.setStartDate(loan.getStartDate());
        dto.setEndDate(loan.getEndDate());
        dto.setStatus(loan.getStatus());
        if (loan.getBorrower() != null) {
            dto.setBorrowerName(loan.getBorrower().getName());
        }
        return dto;
    }
}

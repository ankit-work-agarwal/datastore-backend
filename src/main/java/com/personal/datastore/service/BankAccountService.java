package com.personal.datastore.service;

import com.personal.datastore.dto.BankAccountDTO;
import com.personal.datastore.exception.ResourceNotFoundException;
import com.personal.datastore.model.BankAccount;
import com.personal.datastore.model.FamilyMember;
import com.personal.datastore.repository.BankAccountRepository;
import com.personal.datastore.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository repository;

    @Autowired
    private FamilyRepository familyRepository;

    public BankAccountDTO save(BankAccount account) {
        if (account.getOwner() != null && account.getOwner().getId() != null) {
            FamilyMember owner = familyRepository
                    .findById(account.getOwner().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Family member not found with id: " + account.getOwner().getId()));
            account.setOwner(owner);
        }
        return mapToDTO(repository.save(account));
    }

    public List<BankAccountDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bank account not found with id: " + id));
        repository.deleteById(id);
    }

    private BankAccountDTO mapToDTO(BankAccount account) {
        BankAccountDTO dto = new BankAccountDTO();
        dto.setId(account.getId());
        dto.setAccountType(account.getAccountType());
        dto.setBankName(account.getBankName());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setIfscCode(account.getIfscCode());
        dto.setBranchName(account.getBranchName());
        dto.setBalance(account.getBalance());
        dto.setOpenedDate(account.getOpenedDate());
        return dto;
    }
}


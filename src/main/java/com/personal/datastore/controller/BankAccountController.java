package com.personal.datastore.controller;

import com.personal.datastore.dto.BankAccountDTO;
import com.personal.datastore.model.BankAccount;
import com.personal.datastore.service.BankAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bank-account")
public class BankAccountController {

    @Autowired
    private BankAccountService service;

    @PostMapping
    public BankAccountDTO add(@Valid @RequestBody BankAccount account) {
        return service.save(account);
    }

    @GetMapping
    public List<BankAccountDTO> getAll(@RequestParam(required = false) Long familyMemberId) {
        if (familyMemberId != null) return service.getByFamilyMemberId(familyMemberId);
        return service.getAll();
    }

    @GetMapping("/{id}")
    public BankAccountDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public BankAccountDTO update(@PathVariable Long id, @Valid @RequestBody BankAccount account) {
        return service.update(id, account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Bank account with id " + id + " deleted successfully");
    }
}

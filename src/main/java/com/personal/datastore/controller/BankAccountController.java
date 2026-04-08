package com.personal.datastore.controller;

import com.personal.datastore.dto.BankAccountDTO;
import com.personal.datastore.model.BankAccount;
import com.personal.datastore.service.BankAccountService;
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
    public BankAccountDTO add(@RequestBody BankAccount account) {
        return service.save(account);
    }

    @GetMapping
    public List<BankAccountDTO> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public BankAccountDTO update(@PathVariable Long id, @RequestBody BankAccount account) {
        return service.update(id, account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Bank account with id " + id + " deleted successfully");
    }
}

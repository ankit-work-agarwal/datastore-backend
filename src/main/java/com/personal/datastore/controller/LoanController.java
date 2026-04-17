package com.personal.datastore.controller;

import com.personal.datastore.dto.LoanDTO;
import com.personal.datastore.model.Loan;
import com.personal.datastore.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loan")
public class LoanController {

    @Autowired
    private LoanService service;

    @PostMapping
    public LoanDTO add(@Valid @RequestBody Loan loan) {
        return service.save(loan);
    }

    @GetMapping
    public List<LoanDTO> getAll(@RequestParam(required = false) Long familyMemberId) {
        if (familyMemberId != null) return service.getByFamilyMemberId(familyMemberId);
        return service.getAll();
    }

    @GetMapping("/{id}")
    public LoanDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public LoanDTO update(@PathVariable Long id, @Valid @RequestBody Loan loan) {
        return service.update(id, loan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Loan with id " + id + " deleted successfully");
    }
}

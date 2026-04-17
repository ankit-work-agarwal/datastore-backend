package com.personal.datastore.controller;

import com.personal.datastore.dto.InsuranceDTO;
import com.personal.datastore.model.Insurance;
import com.personal.datastore.service.InsuranceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    @Autowired
    private InsuranceService service;

    @PostMapping
    public InsuranceDTO add(@Valid @RequestBody Insurance insurance) {
        return service.save(insurance);
    }

    @GetMapping
    public List<InsuranceDTO> getAll(@RequestParam(required = false) Long familyMemberId) {
        if (familyMemberId != null) return service.getByFamilyMemberId(familyMemberId);
        return service.getAll();
    }

    @GetMapping("/{id}")
    public InsuranceDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public InsuranceDTO update(@PathVariable Long id, @Valid @RequestBody Insurance insurance) {
        return service.update(id, insurance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Insurance with id " + id + " deleted successfully");
    }
}

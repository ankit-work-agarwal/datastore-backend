package com.personal.datastore.controller;

import com.personal.datastore.dto.InsuranceDTO;
import com.personal.datastore.model.Insurance;
import com.personal.datastore.service.InsuranceService;
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
    public InsuranceDTO add(@RequestBody Insurance insurance) {
        return service.save(insurance);
    }

    @GetMapping
    public List<InsuranceDTO> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public InsuranceDTO update(@PathVariable Long id, @RequestBody Insurance insurance) {
        return service.update(id, insurance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Insurance with id " + id + " deleted successfully");
    }
}

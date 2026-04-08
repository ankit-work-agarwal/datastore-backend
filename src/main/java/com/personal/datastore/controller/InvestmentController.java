package com.personal.datastore.controller;

import com.personal.datastore.dto.InvestmentDTO;
import com.personal.datastore.model.Investment;
import com.personal.datastore.service.InvestmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investment")
public class InvestmentController {

    @Autowired
    private InvestmentService service;

    @PostMapping
    public InvestmentDTO add(@RequestBody Investment investment) {
        return service.save(investment);
    }

    @GetMapping
    public List<InvestmentDTO> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Investment with id " + id + " deleted successfully");
    }
}


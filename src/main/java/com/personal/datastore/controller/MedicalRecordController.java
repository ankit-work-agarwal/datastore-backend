package com.personal.datastore.controller;

import com.personal.datastore.dto.MedicalRecordDTO;
import com.personal.datastore.model.MedicalRecord;
import com.personal.datastore.service.MedicalRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical-record")
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService service;

    @PostMapping
    public MedicalRecordDTO add(@Valid @RequestBody MedicalRecord record) {
        return service.save(record);
    }

    @GetMapping
    public List<MedicalRecordDTO> getAll(@RequestParam(required = false) Long familyMemberId) {
        if (familyMemberId != null) return service.getByFamilyMemberId(familyMemberId);
        return service.getAll();
    }

    @GetMapping("/{id}")
    public MedicalRecordDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public MedicalRecordDTO update(@PathVariable Long id, @Valid @RequestBody MedicalRecord record) {
        return service.update(id, record);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Medical record with id " + id + " deleted successfully");
    }
}

package com.personal.datastore.controller;

import com.personal.datastore.dto.MedicalRecordDTO;
import com.personal.datastore.model.MedicalRecord;
import com.personal.datastore.service.MedicalRecordService;
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
    public MedicalRecordDTO add(@RequestBody MedicalRecord record) {
        return service.save(record);
    }

    @GetMapping
    public List<MedicalRecordDTO> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Medical record with id " + id + " deleted successfully");
    }
}


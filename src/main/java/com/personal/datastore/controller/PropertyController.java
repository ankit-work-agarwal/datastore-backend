package com.personal.datastore.controller;

import com.personal.datastore.dto.PropertyDTO;
import com.personal.datastore.model.Property;
import com.personal.datastore.service.PropertyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    private PropertyService service;

    @PostMapping
    public PropertyDTO add(@Valid @RequestBody Property property) {
        return service.save(property);
    }

    @GetMapping
    public List<PropertyDTO> getAll(@RequestParam(required = false) Long familyMemberId) {
        if (familyMemberId != null) return service.getByFamilyMemberId(familyMemberId);
        return service.getAll();
    }

    @GetMapping("/{id}")
    public PropertyDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public PropertyDTO update(@PathVariable Long id, @Valid @RequestBody Property property) {
        return service.update(id, property);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Property with id " + id + " deleted successfully");
    }
}

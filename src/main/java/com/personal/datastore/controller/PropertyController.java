package com.personal.datastore.controller;

import com.personal.datastore.dto.PropertyDTO;
import com.personal.datastore.model.Property;
import com.personal.datastore.service.PropertyService;
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
    public PropertyDTO add(@RequestBody Property property) {
        return service.save(property);
    }

    @GetMapping
    public List<PropertyDTO> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Property with id " + id + " deleted successfully");
    }
}


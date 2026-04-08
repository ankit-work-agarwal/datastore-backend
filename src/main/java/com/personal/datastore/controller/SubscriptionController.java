package com.personal.datastore.controller;

import com.personal.datastore.dto.SubscriptionDTO;
import com.personal.datastore.model.Subscription;
import com.personal.datastore.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService service;

    @PostMapping
    public SubscriptionDTO add(@RequestBody Subscription subscription) {
        return service.save(subscription);
    }

    @GetMapping
    public List<SubscriptionDTO> getAll() {
        return service.getAll();
    }

    @PutMapping("/{id}")
    public SubscriptionDTO update(@PathVariable Long id, @RequestBody Subscription subscription) {
        return service.update(id, subscription);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Subscription with id " + id + " deleted successfully");
    }
}


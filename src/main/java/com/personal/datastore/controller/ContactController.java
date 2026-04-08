package com.personal.datastore.controller;

import com.personal.datastore.dto.ContactDTO;
import com.personal.datastore.model.Contact;
import com.personal.datastore.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactService service;

    @PostMapping
    public ContactDTO add(@RequestBody Contact contact) {
        return service.save(contact);
    }

    @GetMapping
    public List<ContactDTO> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Contact with id " + id + " deleted successfully");
    }
}


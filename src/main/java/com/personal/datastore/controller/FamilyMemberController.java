package com.personal.datastore.controller;

import com.personal.datastore.dto.FamilyMemberDTO;
import com.personal.datastore.model.FamilyMember;
import com.personal.datastore.service.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/family")
public class FamilyMemberController {

    @Autowired
    private FamilyService service;

    @PostMapping
    public FamilyMember addMember(@RequestBody FamilyMember member) {
        return service.addMember(member);
    }

    @GetMapping
    public List<FamilyMemberDTO> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Family member with id " + id + " deleted successfully");
    }
}


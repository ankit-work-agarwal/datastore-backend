package com.personal.datastore.service;

import com.personal.datastore.model.FamilyMember;
import com.personal.datastore.repository.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamilyService {

    @Autowired
    private FamilyRepository repository;

    public FamilyMember addMember(FamilyMember member) {
        return repository.save(member);
    }

    public List<FamilyMember> getAllMembers() {
        return repository.findAll();
    }
}
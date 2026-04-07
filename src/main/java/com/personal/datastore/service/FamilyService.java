package com.personal.datastore.service;

import com.personal.datastore.model.FamilyMember;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FamilyService {

    private List<FamilyMember> familyList = new ArrayList<>();

    public FamilyMember addMember(FamilyMember member) {
        familyList.add(member);
        return member;
    }

    public List<FamilyMember> getAllMembers() {
        return familyList;
    }
}
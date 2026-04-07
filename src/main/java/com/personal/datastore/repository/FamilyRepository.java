package com.personal.datastore.repository;

import com.personal.datastore.model.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<FamilyMember, Long> {
}
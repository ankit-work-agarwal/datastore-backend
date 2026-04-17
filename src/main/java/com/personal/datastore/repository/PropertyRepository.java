package com.personal.datastore.repository;

import com.personal.datastore.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByOwner_Id(Long familyMemberId);
}

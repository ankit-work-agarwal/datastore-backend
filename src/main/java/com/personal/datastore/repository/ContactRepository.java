package com.personal.datastore.repository;

import com.personal.datastore.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    List<Contact> findByAddedBy_Id(Long familyMemberId);
}

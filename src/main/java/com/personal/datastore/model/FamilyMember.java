package com.personal.datastore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.personal.datastore.dto.DocumentDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class FamilyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String relation;
    private String phone;
    private String email;

    @JsonManagedReference("vehicle-ref")
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;

    @JsonManagedReference("document-ref")
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Document> documents;
}
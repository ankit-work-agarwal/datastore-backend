package com.personal.datastore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String type; // Aadhaar, PAN, etc.
    private String filePath;
    private LocalDate expiryDate;

    @JsonBackReference("document-ref")
    @ManyToOne
    @JoinColumn(name = "family_member_id")
    private FamilyMember owner;
}
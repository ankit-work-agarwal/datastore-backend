package com.personal.datastore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Document type is required")
    private String type; // Aadhaar, PAN, etc.

    private String filePath;
    private LocalDate expiryDate;

    @JsonBackReference("document-ref")
    @ManyToOne
    @JoinColumn(name = "family_member_id")
    private FamilyMember owner;
}
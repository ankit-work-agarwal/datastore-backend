package com.personal.datastore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Record type is required")
    private String type;         // Diagnosis, Prescription, Lab Report, Vaccination, etc.

    @NotBlank(message = "Title is required")
    private String title;

    private String doctorName;
    private String hospitalName;

    @NotNull(message = "Record date is required")
    private LocalDate recordDate;

    @Column(length = 1000)
    private String notes;

    private String filePath;     // Attached report/prescription scan

    @JsonBackReference("medical-ref")
    @ManyToOne
    @JoinColumn(name = "family_member_id")
    private FamilyMember member;
}


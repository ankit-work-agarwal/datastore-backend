package com.personal.datastore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;         // Diagnosis, Prescription, Lab Report, Vaccination, etc.
    private String title;
    private String doctorName;
    private String hospitalName;
    private LocalDate recordDate;

    @Column(length = 1000)
    private String notes;

    private String filePath;     // Attached report/prescription scan

    @JsonBackReference("medical-ref")
    @ManyToOne
    @JoinColumn(name = "family_member_id")
    private FamilyMember member;
}


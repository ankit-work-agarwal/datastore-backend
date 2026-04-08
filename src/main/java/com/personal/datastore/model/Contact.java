package com.personal.datastore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String category;     // Doctor, Lawyer, Financial Advisor, Emergency, etc.
    private String phone;
    private String email;
    private String organization; // Hospital, Firm, Bank, etc.

    @Column(length = 500)
    private String notes;

    @JsonBackReference("contact-ref")
    @ManyToOne
    @JoinColumn(name = "family_member_id")
    private FamilyMember addedBy;
}


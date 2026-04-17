package com.personal.datastore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Category is required")
    private String category;     // Doctor, Lawyer, Financial Advisor, Emergency, etc.

    private String phone;

    @Email(message = "Email must be valid")
    private String email;

    private String organization; // Hospital, Firm, Bank, etc.

    @Column(length = 500)
    private String notes;

    @JsonBackReference("contact-ref")
    @ManyToOne
    @JoinColumn(name = "family_member_id")
    private FamilyMember addedBy;
}


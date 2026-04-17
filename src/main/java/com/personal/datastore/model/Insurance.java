package com.personal.datastore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Insurance type is required")
    private String type;          // Life, Health, Vehicle, Home, etc.

    @NotBlank(message = "Policy number is required")
    private String policyNumber;

    @NotBlank(message = "Provider is required")
    private String provider;      // Insurance company name

    @NotNull(message = "Premium amount is required")
    @Positive(message = "Premium amount must be positive")
    private BigDecimal premiumAmount;

    private BigDecimal sumAssured;

    private LocalDate startDate;
    private LocalDate expiryDate;

    @JsonBackReference("insurance-ref")
    @ManyToOne
    @JoinColumn(name = "family_member_id")
    private FamilyMember holder;
}


package com.personal.datastore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;          // Life, Health, Vehicle, Home, etc.
    private String policyNumber;
    private String provider;      // Insurance company name

    private BigDecimal premiumAmount;
    private BigDecimal sumAssured;

    private LocalDate startDate;
    private LocalDate expiryDate;

    @JsonBackReference("insurance-ref")
    @ManyToOne
    @JoinColumn(name = "family_member_id")
    private FamilyMember holder;
}


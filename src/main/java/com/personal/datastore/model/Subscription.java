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
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Subscription name is required")
    private String name;           // Netflix, Spotify, Gym, etc.

    @NotBlank(message = "Category is required")
    private String category;       // OTT, Music, Fitness, Software, Magazine, etc.

    @NotNull(message = "Amount is required")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount;

    @NotBlank(message = "Billing cycle is required")
    private String billingCycle;   // Monthly, Quarterly, Yearly

    private LocalDate startDate;
    private LocalDate renewalDate;
    private Boolean isActive;

    @Column(length = 500)
    private String notes;

    @JsonBackReference("subscription-ref")
    @ManyToOne
    @JoinColumn(name = "family_member_id")
    private FamilyMember owner;
}


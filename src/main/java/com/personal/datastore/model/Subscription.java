package com.personal.datastore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;           // Netflix, Spotify, Gym, etc.
    private String category;       // OTT, Music, Fitness, Software, Magazine, etc.
    private BigDecimal amount;
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


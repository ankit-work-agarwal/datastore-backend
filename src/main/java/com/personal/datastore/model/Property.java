package com.personal.datastore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;            // House, Apartment, Plot, Commercial, etc.
    private String address;
    private String city;
    private String state;

    private LocalDate purchaseDate;
    private BigDecimal purchaseValue;
    private BigDecimal currentValue;

    private Boolean isRented;
    private BigDecimal rentalIncome; // Monthly rental income, if rented

    @JsonBackReference("property-ref")
    @ManyToOne
    @JoinColumn(name = "family_member_id")
    private FamilyMember owner;
}


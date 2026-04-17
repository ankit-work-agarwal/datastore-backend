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
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Property type is required")
    private String type;            // House, Apartment, Plot, Commercial, etc.

    @NotBlank(message = "Address is required")
    private String address;

    private String city;
    private String state;
    private LocalDate purchaseDate;

    @NotNull(message = "Purchase value is required")
    @Positive(message = "Purchase value must be positive")
    private BigDecimal purchaseValue;

    private BigDecimal currentValue;

    private Boolean isRented;
    private BigDecimal rentalIncome; // Monthly rental income, if rented

    @JsonBackReference("property-ref")
    @ManyToOne
    @JoinColumn(name = "family_member_id")
    private FamilyMember owner;
}


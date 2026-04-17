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
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Investment type is required")
    private String type;        // MutualFund, FD, Stock, PPF, NPS, etc.

    @NotBlank(message = "Investment name is required")
    private String name;        // Fund/scheme name

    private String institution; // Bank or broker name

    @NotNull(message = "Invested amount is required")
    @Positive(message = "Invested amount must be positive")
    private BigDecimal investedAmount;

    private BigDecimal currentValue;

    private LocalDate startDate;
    private LocalDate maturityDate;

    @JsonBackReference("investment-ref")
    @ManyToOne
    @JoinColumn(name = "family_member_id")
    private FamilyMember holder;
}


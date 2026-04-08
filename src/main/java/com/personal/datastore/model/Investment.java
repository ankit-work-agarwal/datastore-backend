package com.personal.datastore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;        // MutualFund, FD, Stock, PPF, NPS, etc.
    private String name;        // Fund/scheme name
    private String institution; // Bank or broker name

    private BigDecimal investedAmount;
    private BigDecimal currentValue;

    private LocalDate startDate;
    private LocalDate maturityDate;

    @JsonBackReference("investment-ref")
    @ManyToOne
    @JoinColumn(name = "family_member_id")
    private FamilyMember holder;
}


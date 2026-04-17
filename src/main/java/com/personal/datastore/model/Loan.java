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
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Loan type is required")
    private String loanType;          // Home, Personal, Vehicle, Education, Business, etc.

    @NotBlank(message = "Bank name is required")
    private String bankName;

    private String accountNumber;

    @NotNull(message = "Principal amount is required")
    @Positive(message = "Principal amount must be positive")
    private BigDecimal principalAmount;

    private BigDecimal outstandingAmount;

    @NotNull(message = "Interest rate is required")
    @Positive(message = "Interest rate must be positive")
    private BigDecimal interestRate;   // Annual interest rate in %

    private BigDecimal emiAmount;
    private LocalDate startDate;
    private LocalDate endDate;

    @NotBlank(message = "Status is required")
    private String status;             // Active, Closed, Overdue

    @JsonBackReference("loan-ref")
    @ManyToOne
    @JoinColumn(name = "family_member_id")
    private FamilyMember borrower;
}


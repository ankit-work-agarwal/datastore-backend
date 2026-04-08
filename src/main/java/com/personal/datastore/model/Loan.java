package com.personal.datastore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String loanType;          // Home, Personal, Vehicle, Education, Business, etc.
    private String bankName;
    private String accountNumber;

    private BigDecimal principalAmount;
    private BigDecimal outstandingAmount;
    private BigDecimal interestRate;   // Annual interest rate in %
    private BigDecimal emiAmount;

    private LocalDate startDate;
    private LocalDate endDate;

    private String status;             // Active, Closed, Overdue

    @JsonBackReference("loan-ref")
    @ManyToOne
    @JoinColumn(name = "family_member_id")
    private FamilyMember borrower;
}


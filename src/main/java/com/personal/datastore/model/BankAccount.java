package com.personal.datastore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountType;   // Savings, Current, FD, Loan, etc.
    private String bankName;
    private String accountNumber;
    private String ifscCode;
    private String branchName;
    private BigDecimal balance;
    private LocalDate openedDate;

    @JsonBackReference("bank-account-ref")
    @ManyToOne
    @JoinColumn(name = "family_member_id")
    private FamilyMember owner;
}


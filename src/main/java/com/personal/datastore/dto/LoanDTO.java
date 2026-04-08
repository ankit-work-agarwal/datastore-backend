package com.personal.datastore.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonPropertyOrder({"id", "loanType", "bankName", "accountNumber", "principalAmount", "outstandingAmount", "interestRate", "emiAmount", "startDate", "endDate", "status"})
public class LoanDTO {

    private Long id;
    private String loanType;
    private String bankName;
    private String accountNumber;
    private BigDecimal principalAmount;
    private BigDecimal outstandingAmount;
    private BigDecimal interestRate;
    private BigDecimal emiAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
}


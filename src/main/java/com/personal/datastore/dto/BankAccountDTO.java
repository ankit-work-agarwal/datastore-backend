package com.personal.datastore.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonPropertyOrder({"id", "accountType", "bankName", "accountNumber", "ifscCode", "branchName", "balance", "openedDate"})
public class BankAccountDTO {

    private Long id;
    private String accountType;
    private String bankName;
    private String accountNumber;
    private String ifscCode;
    private String branchName;
    private BigDecimal balance;
    private LocalDate openedDate;
}


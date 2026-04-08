package com.personal.datastore.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonPropertyOrder({"id", "type", "policyNumber", "provider", "premiumAmount", "sumAssured", "startDate", "expiryDate"})
public class InsuranceDTO {

    private Long id;
    private String type;
    private String policyNumber;
    private String provider;
    private BigDecimal premiumAmount;
    private BigDecimal sumAssured;
    private LocalDate startDate;
    private LocalDate expiryDate;
}


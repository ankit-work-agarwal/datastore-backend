package com.personal.datastore.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonPropertyOrder({"id", "type", "name", "institution", "investedAmount", "currentValue", "startDate", "maturityDate", "holderName"})
public class InvestmentDTO {

    private Long id;
    private String type;
    private String name;
    private String institution;
    private BigDecimal investedAmount;
    private BigDecimal currentValue;
    private LocalDate startDate;
    private LocalDate maturityDate;
    private String holderName;
}


package com.personal.datastore.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonPropertyOrder({"id", "name", "category", "amount", "billingCycle", "startDate", "renewalDate", "isActive", "notes", "ownerName"})
public class SubscriptionDTO {

    private Long id;
    private String name;
    private String category;
    private BigDecimal amount;
    private String billingCycle;
    private LocalDate startDate;
    private LocalDate renewalDate;
    private Boolean isActive;
    private String notes;
    private String ownerName;
}


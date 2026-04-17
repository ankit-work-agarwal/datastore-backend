package com.personal.datastore.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonPropertyOrder({
        "familyMembers",
        "vehicles",
        "documents",
        "investments",
        "insurances",
        "medicalRecords",
        "properties",
        "bankAccounts",
        "contacts",
        "subscriptions",
        "loans",
        "totalInvestedAmount",
        "totalCurrentInvestmentValue",
        "totalPropertyValue",
        "totalOutstandingLoanAmount",
        "totalMonthlySubscriptionCost",
        "activeSubscriptions"
})
public class DashboardDTO {

    // Counts
    private long familyMembers;
    private long vehicles;
    private long documents;
    private long investments;
    private long insurances;
    private long medicalRecords;
    private long properties;
    private long bankAccounts;
    private long contacts;
    private long subscriptions;
    private long loans;

    // Financial summaries
    private BigDecimal totalInvestedAmount;
    private BigDecimal totalCurrentInvestmentValue;
    private BigDecimal totalPropertyValue;
    private BigDecimal totalOutstandingLoanAmount;
    private BigDecimal totalMonthlySubscriptionCost;
    private long activeSubscriptions;
}


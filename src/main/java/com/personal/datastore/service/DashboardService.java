package com.personal.datastore.service;

import com.personal.datastore.dto.DashboardDTO;
import com.personal.datastore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DashboardService {

    @Autowired private FamilyRepository familyRepository;
    @Autowired private VehicleRepository vehicleRepository;
    @Autowired private DocumentRepository documentRepository;
    @Autowired private InvestmentRepository investmentRepository;
    @Autowired private InsuranceRepository insuranceRepository;
    @Autowired private MedicalRecordRepository medicalRecordRepository;
    @Autowired private PropertyRepository propertyRepository;
    @Autowired private BankAccountRepository bankAccountRepository;
    @Autowired private ContactRepository contactRepository;
    @Autowired private SubscriptionRepository subscriptionRepository;
    @Autowired private LoanRepository loanRepository;

    public DashboardDTO getSummary() {
        DashboardDTO dto = new DashboardDTO();

        // Counts
        dto.setFamilyMembers(familyRepository.count());
        dto.setVehicles(vehicleRepository.count());
        dto.setDocuments(documentRepository.count());
        dto.setInvestments(investmentRepository.count());
        dto.setInsurances(insuranceRepository.count());
        dto.setMedicalRecords(medicalRecordRepository.count());
        dto.setProperties(propertyRepository.count());
        dto.setBankAccounts(bankAccountRepository.count());
        dto.setContacts(contactRepository.count());
        dto.setSubscriptions(subscriptionRepository.count());
        dto.setLoans(loanRepository.count());

        // Financial summaries — safely handle nulls with stream reduce
        BigDecimal totalInvested = investmentRepository.findAll().stream()
                .map(i -> i.getInvestedAmount() != null ? i.getInvestedAmount() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        dto.setTotalInvestedAmount(totalInvested);

        BigDecimal totalCurrentValue = investmentRepository.findAll().stream()
                .map(i -> i.getCurrentValue() != null ? i.getCurrentValue() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        dto.setTotalCurrentInvestmentValue(totalCurrentValue);

        BigDecimal totalPropertyValue = propertyRepository.findAll().stream()
                .map(p -> p.getCurrentValue() != null ? p.getCurrentValue()
                        : (p.getPurchaseValue() != null ? p.getPurchaseValue() : BigDecimal.ZERO))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        dto.setTotalPropertyValue(totalPropertyValue);

        BigDecimal totalOutstandingLoan = loanRepository.findAll().stream()
                .map(l -> l.getOutstandingAmount() != null ? l.getOutstandingAmount() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        dto.setTotalOutstandingLoanAmount(totalOutstandingLoan);

        // Monthly-equivalent subscription cost
        BigDecimal totalSubscriptionCost = subscriptionRepository.findAll().stream()
                .filter(s -> Boolean.TRUE.equals(s.getIsActive()) && s.getAmount() != null)
                .map(s -> {
                    BigDecimal amount = s.getAmount();
                    if ("Yearly".equalsIgnoreCase(s.getBillingCycle())) {
                        return amount.divide(BigDecimal.valueOf(12), 2, java.math.RoundingMode.HALF_UP);
                    } else if ("Quarterly".equalsIgnoreCase(s.getBillingCycle())) {
                        return amount.divide(BigDecimal.valueOf(3), 2, java.math.RoundingMode.HALF_UP);
                    }
                    return amount; // Monthly
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        dto.setTotalMonthlySubscriptionCost(totalSubscriptionCost);

        long activeSubscriptions = subscriptionRepository.findAll().stream()
                .filter(s -> Boolean.TRUE.equals(s.getIsActive()))
                .count();
        dto.setActiveSubscriptions(activeSubscriptions);

        return dto;
    }
}


package com.personal.datastore.service;

import com.personal.datastore.dto.DashboardDTO;
import com.personal.datastore.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

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

        // Run all DB queries in parallel instead of sequentially
        CompletableFuture<Long> familyCount       = CompletableFuture.supplyAsync(familyRepository::count);
        CompletableFuture<Long> vehicleCount      = CompletableFuture.supplyAsync(vehicleRepository::count);
        CompletableFuture<Long> documentCount     = CompletableFuture.supplyAsync(documentRepository::count);
        CompletableFuture<Long> investmentCount   = CompletableFuture.supplyAsync(investmentRepository::count);
        CompletableFuture<Long> insuranceCount    = CompletableFuture.supplyAsync(insuranceRepository::count);
        CompletableFuture<Long> medicalCount      = CompletableFuture.supplyAsync(medicalRecordRepository::count);
        CompletableFuture<Long> propertyCount     = CompletableFuture.supplyAsync(propertyRepository::count);
        CompletableFuture<Long> bankCount         = CompletableFuture.supplyAsync(bankAccountRepository::count);
        CompletableFuture<Long> contactCount      = CompletableFuture.supplyAsync(contactRepository::count);
        CompletableFuture<Long> subscriptionCount = CompletableFuture.supplyAsync(subscriptionRepository::count);
        CompletableFuture<Long> loanCount         = CompletableFuture.supplyAsync(loanRepository::count);

        // Financial aggregations pushed to DB — no more findAll() in memory
        CompletableFuture<BigDecimal> totalInvested      = CompletableFuture.supplyAsync(investmentRepository::sumInvestedAmount);
        CompletableFuture<BigDecimal> totalCurrentValue  = CompletableFuture.supplyAsync(investmentRepository::sumCurrentValue);
        CompletableFuture<BigDecimal> totalPropertyValue = CompletableFuture.supplyAsync(propertyRepository::sumPropertyValue);
        CompletableFuture<BigDecimal> totalOutstanding   = CompletableFuture.supplyAsync(loanRepository::sumOutstandingAmount);
        CompletableFuture<Long> activeSubCount           = CompletableFuture.supplyAsync(subscriptionRepository::countActiveSubscriptions);

        // Subscription monthly cost still needs Java calculation (billing cycle normalization)
        CompletableFuture<BigDecimal> totalSubCost = CompletableFuture.supplyAsync(() -> {
            return subscriptionRepository.findActiveWithAmount().stream()
                    .map(s -> {
                        BigDecimal amount = s.getAmount();
                        if ("Yearly".equalsIgnoreCase(s.getBillingCycle())) {
                            return amount.divide(BigDecimal.valueOf(12), 2, java.math.RoundingMode.HALF_UP);
                        } else if ("Quarterly".equalsIgnoreCase(s.getBillingCycle())) {
                            return amount.divide(BigDecimal.valueOf(3), 2, java.math.RoundingMode.HALF_UP);
                        }
                        return amount;
                    })
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        });

        // Wait for all futures to complete
        CompletableFuture.allOf(
                familyCount, vehicleCount, documentCount, investmentCount, insuranceCount,
                medicalCount, propertyCount, bankCount, contactCount, subscriptionCount,
                loanCount, totalInvested, totalCurrentValue, totalPropertyValue,
                totalOutstanding, activeSubCount, totalSubCost
        ).join();

        DashboardDTO dto = new DashboardDTO();
        dto.setFamilyMembers(familyCount.join());
        dto.setVehicles(vehicleCount.join());
        dto.setDocuments(documentCount.join());
        dto.setInvestments(investmentCount.join());
        dto.setInsurances(insuranceCount.join());
        dto.setMedicalRecords(medicalCount.join());
        dto.setProperties(propertyCount.join());
        dto.setBankAccounts(bankCount.join());
        dto.setContacts(contactCount.join());
        dto.setSubscriptions(subscriptionCount.join());
        dto.setLoans(loanCount.join());
        dto.setTotalInvestedAmount(totalInvested.join());
        dto.setTotalCurrentInvestmentValue(totalCurrentValue.join());
        dto.setTotalPropertyValue(totalPropertyValue.join());
        dto.setTotalOutstandingLoanAmount(totalOutstanding.join());
        dto.setActiveSubscriptions(activeSubCount.join());
        dto.setTotalMonthlySubscriptionCost(totalSubCost.join());

        return dto;
    }
}

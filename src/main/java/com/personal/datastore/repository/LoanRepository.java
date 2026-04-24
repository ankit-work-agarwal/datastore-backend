package com.personal.datastore.repository;

import com.personal.datastore.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByBorrower_Id(Long familyMemberId);

    @Query("SELECT COALESCE(SUM(l.outstandingAmount), 0) FROM Loan l WHERE l.outstandingAmount IS NOT NULL")
    BigDecimal sumOutstandingAmount();
}

package com.personal.datastore.repository;

import com.personal.datastore.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByBorrower_Id(Long familyMemberId);
}

package com.personal.datastore.repository;

import com.personal.datastore.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    List<Investment> findByHolder_Id(Long familyMemberId);

    @Query("SELECT COALESCE(SUM(i.investedAmount), 0) FROM Investment i")
    BigDecimal sumInvestedAmount();

    @Query("SELECT COALESCE(SUM(i.currentValue), 0) FROM Investment i WHERE i.currentValue IS NOT NULL")
    BigDecimal sumCurrentValue();
}

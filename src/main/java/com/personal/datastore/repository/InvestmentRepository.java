package com.personal.datastore.repository;

import com.personal.datastore.model.Investment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    List<Investment> findByHolder_Id(Long familyMemberId);
}

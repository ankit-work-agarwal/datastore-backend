package com.personal.datastore.repository;

import com.personal.datastore.model.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    List<Insurance> findByHolder_Id(Long familyMemberId);
}

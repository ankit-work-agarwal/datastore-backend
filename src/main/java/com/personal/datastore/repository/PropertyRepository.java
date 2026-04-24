package com.personal.datastore.repository;

import com.personal.datastore.model.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    List<Property> findByOwner_Id(Long familyMemberId);

    @Query("SELECT COALESCE(SUM(CASE WHEN p.currentValue IS NOT NULL THEN p.currentValue ELSE p.purchaseValue END), 0) FROM Property p")
    BigDecimal sumPropertyValue();
}

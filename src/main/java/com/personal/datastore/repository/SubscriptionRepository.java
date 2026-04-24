package com.personal.datastore.repository;

import com.personal.datastore.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByOwner_Id(Long familyMemberId);

    @Query("SELECT COUNT(s) FROM Subscription s WHERE s.isActive = true")
    long countActiveSubscriptions();

    @Query("SELECT s FROM Subscription s WHERE s.isActive = true AND s.amount IS NOT NULL")
    List<Subscription> findActiveWithAmount();
}

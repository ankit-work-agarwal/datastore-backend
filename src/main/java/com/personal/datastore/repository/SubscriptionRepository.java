package com.personal.datastore.repository;

import com.personal.datastore.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByOwner_Id(Long familyMemberId);
}

package com.personal.datastore.repository;

import com.personal.datastore.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByOwner_Id(Long familyMemberId);
}
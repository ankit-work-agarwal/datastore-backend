package com.personal.datastore.repository;

import com.personal.datastore.model.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
    List<MedicalRecord> findByMember_Id(Long familyMemberId);
}

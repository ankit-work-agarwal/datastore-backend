package com.personal.datastore.service;

import com.personal.datastore.dto.MedicalRecordDTO;
import com.personal.datastore.exception.ResourceNotFoundException;
import com.personal.datastore.model.FamilyMember;
import com.personal.datastore.model.MedicalRecord;
import com.personal.datastore.repository.FamilyRepository;
import com.personal.datastore.repository.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {

    @Autowired
    private MedicalRecordRepository repository;

    @Autowired
    private FamilyRepository familyRepository;

    public MedicalRecordDTO save(MedicalRecord record) {
        if (record.getMember() != null && record.getMember().getId() != null) {
            FamilyMember member = familyRepository
                    .findById(record.getMember().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Family member not found with id: " + record.getMember().getId()));
            record.setMember(member);
        }
        return mapToDTO(repository.save(record));
    }

    public List<MedicalRecordDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public void delete(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Medical record not found with id: " + id));
        repository.deleteById(id);
    }

    private MedicalRecordDTO mapToDTO(MedicalRecord record) {
        MedicalRecordDTO dto = new MedicalRecordDTO();
        dto.setId(record.getId());
        dto.setType(record.getType());
        dto.setTitle(record.getTitle());
        dto.setDoctorName(record.getDoctorName());
        dto.setHospitalName(record.getHospitalName());
        dto.setRecordDate(record.getRecordDate());
        dto.setNotes(record.getNotes());
        dto.setFilePath(record.getFilePath());
        return dto;
    }
}


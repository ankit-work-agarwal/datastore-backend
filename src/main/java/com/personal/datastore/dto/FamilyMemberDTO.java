package com.personal.datastore.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import java.util.List;

@Data
@JsonPropertyOrder({"id", "name", "relation", "phone", "email", "vehicles", "documents", "investments", "insurances", "medicalRecords", "properties"})
public class FamilyMemberDTO {

    private Long id;
    private String name;
    private String relation;
    private String phone;
    private String email;

    private List<VehicleDTO> vehicles;
    private List<DocumentDTO> documents;
    private List<InvestmentDTO> investments;
    private List<InsuranceDTO> insurances;
    private List<MedicalRecordDTO> medicalRecords;
    private List<PropertyDTO> properties;
}
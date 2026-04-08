package com.personal.datastore.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import java.util.List;

@Data
@JsonPropertyOrder({"id", "name", "relation", "phone", "email", "vehicles", "documents"})
public class FamilyMemberDTO {

    private Long id;
    private String name;
    private String relation;
    private String phone;
    private String email;

    private List<VehicleDTO> vehicles;
    private List<DocumentDTO> documents;
}
package com.personal.datastore.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name", "category", "phone", "email", "organization", "notes", "addedByName"})
public class ContactDTO {

    private Long id;
    private String name;
    private String category;
    private String phone;
    private String email;
    private String organization;
    private String notes;
    private String addedByName;
}


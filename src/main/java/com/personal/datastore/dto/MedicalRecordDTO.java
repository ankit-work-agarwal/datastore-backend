package com.personal.datastore.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonPropertyOrder({"id", "type", "title", "doctorName", "hospitalName", "recordDate", "notes", "filePath"})
public class MedicalRecordDTO {

    private Long id;
    private String type;
    private String title;
    private String doctorName;
    private String hospitalName;
    private LocalDate recordDate;
    private String notes;
    private String filePath;
}


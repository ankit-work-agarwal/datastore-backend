package com.personal.datastore.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonPropertyOrder({"id", "title", "type", "filePath", "expiryDate", "ownerName"})
public class DocumentDTO {

    private Long id;
    private String title;
    private String type;
    private String filePath;
    private LocalDate expiryDate;
    private String ownerName;
}
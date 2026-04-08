package com.personal.datastore.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "type", "model", "registrationNumber"})
public class VehicleDTO {

    private Long id;
    private String type;
    private String model;
    private String registrationNumber;
}


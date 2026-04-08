package com.personal.datastore.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@JsonPropertyOrder({"id", "type", "address", "city", "state", "purchaseDate", "purchaseValue", "currentValue", "isRented", "rentalIncome"})
public class PropertyDTO {

    private Long id;
    private String type;
    private String address;
    private String city;
    private String state;
    private LocalDate purchaseDate;
    private BigDecimal purchaseValue;
    private BigDecimal currentValue;
    private Boolean isRented;
    private BigDecimal rentalIncome;
}


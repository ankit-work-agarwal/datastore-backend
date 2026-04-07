package com.personal.datastore.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // Car, Bike
    private String model;
    private String registrationNumber;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "family_member_id")
    private FamilyMember owner;
}
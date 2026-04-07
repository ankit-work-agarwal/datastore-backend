package com.personal.datastore.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class FamilyMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String relation;
    private String phone;
    private String email;
}
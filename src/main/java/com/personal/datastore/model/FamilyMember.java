package com.personal.datastore.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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

    @JsonManagedReference("vehicle-ref")
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Vehicle> vehicles;

    @JsonManagedReference("document-ref")
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Document> documents;

    @JsonManagedReference("investment-ref")
    @OneToMany(mappedBy = "holder", cascade = CascadeType.ALL)
    private List<Investment> investments;

    @JsonManagedReference("insurance-ref")
    @OneToMany(mappedBy = "holder", cascade = CascadeType.ALL)
    private List<Insurance> insurances;

    @JsonManagedReference("medical-ref")
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MedicalRecord> medicalRecords;

    @JsonManagedReference("property-ref")
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Property> properties;

    @JsonManagedReference("bank-account-ref")
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<BankAccount> bankAccounts;

    @JsonManagedReference("contact-ref")
    @OneToMany(mappedBy = "addedBy", cascade = CascadeType.ALL)
    private List<Contact> contacts;
}
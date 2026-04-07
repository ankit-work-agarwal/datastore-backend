package com.personal.datastore.model;

public class FamilyMember {

    private Long id;
    private String name;
    private String relation;
    private String phone;
    private String email;

    // Constructors
    public FamilyMember() {}

    public FamilyMember(Long id, String name, String relation, String phone, String email) {
        this.id = id;
        this.name = name;
        this.relation = relation;
        this.phone = phone;
        this.email = email;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRelation() { return relation; }
    public void setRelation(String relation) { this.relation = relation; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
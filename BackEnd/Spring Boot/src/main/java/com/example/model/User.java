package com.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity     // Says that this is mapped to a database table
public class User {
    @Id     // The primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)     // How the primary key is picked
    private Long id;
    //@NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    //@NotBlank(message = "Phone number is required")
    private String phoneNum;
    //@NotBlank(message = "Email is required")
    private String email;
    private String streetNum;
    private String streetName;
    private String streetType;
    private String suburb;
    private String postcode;
    private String state;

    private Date created_At;
    private Date updated_at;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public User() {

    }

    // When it is created, add a created at date
    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
    }

    // Whenever it changes, run this
    @PreUpdate
    protected void onUpdate() {
        this.updated_at = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}

package com.example.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Worker extends User {

    private String profile;

    @ManyToOne
    private Business business;

    @OneToMany
    private List<Booking> bookings;

    public Worker(Long id) {
        super(id);
    }

    public Worker() {

    }

    public Worker(@NotBlank(message = "First name is required") String firstName, @NotBlank(message = "Last name is required") String lastName, @NotBlank(message = "Phone number is required") String phoneNum, @NotBlank(message = "Email is required") String email, String profile) {
        super(firstName, lastName, phoneNum, email);
        this.profile = profile;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }
}

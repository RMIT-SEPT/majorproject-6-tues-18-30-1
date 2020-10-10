package com.example.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

}

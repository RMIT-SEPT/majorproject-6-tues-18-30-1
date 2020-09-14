package com.example.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Worker extends User {

    public Worker(Long id) {
        super(id);
    }

    public Worker() {

    }

    @OneToMany
    private List<Booking> bookings;

    private String profile;

}

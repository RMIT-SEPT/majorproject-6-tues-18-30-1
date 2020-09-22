package com.example.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity     // Says that this is mapped to a database table
public class Customer extends User {

    @OneToMany
    private List<Booking> bookings;

    // Constructors
    public Customer(Long id) {
        super(id);
    }

    public Customer() {

    }

}

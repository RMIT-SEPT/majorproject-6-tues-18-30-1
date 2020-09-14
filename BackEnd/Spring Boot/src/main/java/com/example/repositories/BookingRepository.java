package com.example.repositories;

import org.springframework.data.repository.CrudRepository;
import com.example.model.Booking;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    public List<Booking> findByCustomerId(Long customerId);
}


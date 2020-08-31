package com.example.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.model.Booking;

public interface BookingRepository extends CrudRepository<Booking, Long> {

}


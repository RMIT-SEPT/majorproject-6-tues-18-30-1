package com.example.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.model.Booking;
import com.example.model.Booking;
import com.example.repositories.BookingRepository;

@Service
public class BookingService {
	@Autowired
    private BookingRepository bookingRepository;

    public Booking saveOrUpdateBooking(Booking booking) {

        // business logic - add later
        return bookingRepository.save(booking);
    }

    // Returns all the Bookings for a customer
    public ArrayList<Booking> getBookings(Long customerId) {
        return (ArrayList<Booking>) bookingRepository.findByCustomerId(customerId);
    }

    // Returns the Booking if they exist or a null Booking
    public Optional<Booking> getBooking(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking;
    }

    // Deletes if it exists
    public boolean deleteBooking(Long id) {
        try {
            bookingRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
	
}

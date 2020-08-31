package com.example.web;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Booking;
import com.example.services.BookingService;

@RestController                     // Marks class as a controller
@RequestMapping("/api/Bookings")       // The URI that this controller is connected to
public class BookingController {
	
	//TODO: Fix code for this class so it is appropriate for booking: currently just copied from user class

    @Autowired
    private BookingService BookingService;

    @PostMapping("")                // What to do with a POST request
    public ResponseEntity<Booking> createNewBooking(@RequestBody Booking Booking) {
        Booking Booking1 = BookingService.saveOrUpdateBooking(Booking);
        return new ResponseEntity<Booking>(Booking, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<Booking>> returnBookings() {
        ArrayList<Booking> Bookings = new ArrayList<Booking>();
        Bookings = BookingService.getBookings();
        return new ResponseEntity<ArrayList<Booking>>(Bookings, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id) {
        Optional<Booking> Booking = BookingService.getBooking(id);
        if (Booking.isPresent()) {
            return new ResponseEntity<Booking>(Booking.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    // Messes up the created/updated fields, do we want the id to have to be in the json or pulled from the @PathVariable?
    @PutMapping("/{id}")
    public ResponseEntity<Booking> replaceBooking(@RequestBody Booking Booking, @PathVariable Long id) {
        Booking.setId(id);
        Booking Booking1 = BookingService.saveOrUpdateBooking(Booking);
        return new ResponseEntity<Booking>(Booking, HttpStatus.CREATED);
    }

    // Patch is long, but I like it better than put :/
    @PatchMapping("/{id}")
    public ResponseEntity<Booking> updateBooking(@RequestBody Booking updatedBooking, @PathVariable Long id) {
        Optional<Booking> storedBooking = BookingService.getBooking(id);
        if (!storedBooking.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        if (updatedBooking.getBookingLength() != 0) {
            storedBooking.get().setBookingLength(updatedBooking.getBookingLength());
        }
        
        Booking Booking1 = BookingService.saveOrUpdateBooking(storedBooking.get());
        return new ResponseEntity<Booking>(storedBooking.get(), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBooking(@PathVariable Long id) {
        if (BookingService.deleteBooking(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

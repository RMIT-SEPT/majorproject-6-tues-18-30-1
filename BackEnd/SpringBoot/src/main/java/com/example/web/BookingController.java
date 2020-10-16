package com.example.web;

import java.util.ArrayList;
import java.util.Optional;

import com.example.model.Customer;
import com.example.model.Service;
import com.example.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Booking;
import com.example.services.BookingService;

@RestController                     // Marks class as a controller
@RequestMapping("/api")       // The URI that this controller is connected to
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // Make a new booking for a customer
    @PostMapping("/customers/{customerId}/bookings")
    public ResponseEntity<Booking> createNewBooking(@RequestBody Booking booking, @PathVariable Long customerId) {
        booking.setCustomer(new Customer(customerId));
        //booking.setService(new Service(serviceID));
        //booking.setWorker(new Worker(workerID));
        Booking booking1 = bookingService.saveOrUpdateBooking(booking);
        return new ResponseEntity<Booking>(booking, HttpStatus.CREATED);
    }
    // Get all of the customers bookings
    @GetMapping("/customers/{customerId}/bookings")
    public ResponseEntity<ArrayList<Booking>> returnBookings(@PathVariable Long customerId) {
        ArrayList<Booking> bookings = new ArrayList<Booking>();
        bookings = bookingService.getBookings(customerId);
        return new ResponseEntity<ArrayList<Booking>>(bookings, HttpStatus.OK);
    }

    @GetMapping("/customers/{customerId}/bookings/{id}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long id, @PathVariable Long customerId) {
        Optional<Booking> booking = bookingService.getBooking(id);
        if (booking.isPresent()) {
            return new ResponseEntity<Booking>(booking.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    // Messes up the created/updated fields, do we want the id to have to be in the json or pulled from the @PathVariable?
    @PutMapping("/customers/{customerId}/bookings/{id}")
    public ResponseEntity<Booking> replaceBooking(@RequestBody Booking booking, @PathVariable Long id,  @PathVariable Long customerId) {
        booking.setId(id);
        booking.setCustomer(new Customer(customerId));
        Booking booking1 = bookingService.saveOrUpdateBooking(booking);
        return new ResponseEntity<Booking>(booking, HttpStatus.CREATED);
    }

    @DeleteMapping("/customers/{customerId}/bookings/{id}")
    public ResponseEntity deleteBooking(@PathVariable Long id, @PathVariable Long customerId) {
        if (bookingService.deleteBooking(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

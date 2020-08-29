package com.example.demo.backend.product.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backend.product.model.Bookking;
import com.example.demo.backend.product.services.BookingServices;

@RestController
@RequestMapping("/v1")

public class BookingController {
	
	@RequestMapping(value = "/bookings", method = RequestMethod.GET)
	public List getAllBookings() {
		
		return BookingServices.getAllBookings();
		
	}
	
	
	@RequestMapping(value = "/bookings/{id}", method = RequestMethod.GET)
	public Bookking getReservation(@PathVariable String id) {
		return BookingServices.getBooking(id);
	}
	
	
	@Autowired
	private Bookking BookingService;
	
	@RequestMapping(value = "/bookings", method = RequestMethod.POST)
	public void addBooking(@RequestBody Bookking booking) {
		BookingServices.addBooking(booking);

}
	@RequestMapping(value = "/bookings/{id}", method = RequestMethod.PUT)
	public void updateBooking(@RequestBody Bookking booking,@PathVariable String id) {
		BookingServices.updateBooking(id, booking);
	}
	
	
	@RequestMapping(value = "/bookings/{id}", method = RequestMethod.DELETE)
	public void deleteReservation(@PathVariable String id) {
		BookingServices.deleteBooking(id);
	}

}
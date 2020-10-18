package com.example.demo.backend.product.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.product.model.Bookking;
import com.example.demo.backend.product.Repositories.BookingRepository;


@Service
public class BookingServices {

	
	@Autowired
	private static BookingRepository bookingRepository;
	
	
	public static List getAllBookings() {
		
		List Bookings = new ArrayList<>();
		bookingRepository.findAll().forEach(Bookings::add);
		
		return Bookings;
	}
	

	public static Bookking getBooking(String id) {
		return bookingRepository.findAllById(id);
	
	}
	

	public static void addBooking(Bookking booking) {
		bookingRepository.save(booking);
	}
	
	
	public static void updateBooking(Long id,Bookking booking) {
		bookingRepository.save(booking);
	}
	
	public static void deleteBooking(String id) {
		bookingRepository.delete(id);
	}


	public static void updateBooking(String id, Bookking booking) {
		// TODO Auto-generated method stub
		
	}


	
}

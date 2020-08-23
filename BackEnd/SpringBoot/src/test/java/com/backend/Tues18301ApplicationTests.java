package com.backend;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.backend.repositories.BookingRepository;
import com.backend.model.Booking;
import com.backend.model.User;

@SpringBootTest
class Tues18301ApplicationTests {
	
	BookingRepository bookingRepository;

	@Test
	void contextLoads() {
	}
	
	@Test
	void testFindBookingsByName_test1() {
		String firstName = null;
		String lastName = null;
		assertEquals(null, bookingRepository.findByName(firstName, lastName));
	}

	@Test
	void testFindBookingsByName_test2() {
		User jimUser = new User("Jim", "Goer", "", "", "", "", "", 0, "");
		Booking personalTrainingBooking = new Booking(jimUser, 2);
		String firstName = "Jim";
		String lastName = "Goer";
		assertEquals(personalTrainingBooking, bookingRepository.findByName(firstName, lastName));
	}
	

}

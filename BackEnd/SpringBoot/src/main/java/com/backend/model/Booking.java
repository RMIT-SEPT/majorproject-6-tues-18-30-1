package com.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private User client;
	private double bookingLength;
	
	protected Booking() {}
	
	public Booking(User client, double bookingLength) {
		this.client = client;
		this.bookingLength = bookingLength;
	}

	public Long getId() {
		return id;
	}

	public User getClient() {
		return client;
	}

	public double getBookingLength() {
		return bookingLength;
	}
	
}

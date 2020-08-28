package com.backend.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="BOOKINGS")
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@OneToOne
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

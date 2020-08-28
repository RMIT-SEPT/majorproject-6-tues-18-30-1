package com.backend.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USERS")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String emailAddress;
	private String streetNumber;
	private String streetName;
	private String suburb;
	private int postcode;
	private String state;
	
	protected User() {}
	
	public User(String firstName, String lastName, String phoneNumber, String emailAddress,
			String streetNumber, String streetName, String suburb, int postcode, String state) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.emailAddress = emailAddress;
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.suburb = suburb;
		this.postcode = postcode;
		this.state = state;
	}
	
	public Long getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public String getAddress() {
		return streetNumber + " " + streetName;
	}
	public String getSuburb() {
		return suburb;
	}
	public int getPostcode() {
		return postcode;
	}
	public String getState() {
		return state;
	}
	
	
}

package com.example.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


//@Table(name="BOOKINGS")
@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

//	private String bookingName;
//
//	public String getBookingName() {
//		return bookingName;
//	}
//
//	public void setBookingName(String bookingName) {
//		this.bookingName = bookingName;
//	}

	//@Column

	// Some redundant data here, length is described in the service table, end time can be found by adding length to start time
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private int bookingLength; // in minutes

	//@Column(name="service_type")
	//private String serviceType;
	
	@ManyToOne//(fetch = FetchType.LAZY) // Doesn't auto-join the user table (opposite of EAGER) - Not sure why it's not working
	private Customer customer;
	@ManyToOne
	private Service service;
	@ManyToOne
	private Worker worker;
	
//	@ManyToOne
//	private Business business;
//	public Business getBusiness() {
//		return business;
//	}
//
//	public void setBusiness(Business business) {
//		this.business = business;
//	}


    private Date created_At;
    private Date updated_at;

    // Constructors

	public Booking() {

	}


	// Getters and setters


	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public Service getService() {
		return service;
	}

	public void setService(Service service) {
		this.service = service;
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}

	// When it is created, add a created at date
    @PrePersist
    protected void onCreate() {
        this.created_At = new Date();
    }

    // Whenever it changes, run this
    @PreUpdate
    protected void onUpdate() {
        this.updated_at = new Date();
    }

	public Long getId() {
		return id;
	}

	public Customer getCustomer() {
		return customer;
	}

	public double getBookingLength() {
		return bookingLength;
	}
	
    public void setId(Long id) {
		this.id = id;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

//	public void setBookingLength(double bookingLength) {
//		this.bookingLength = bookingLength;
//	}

	public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
    
//	public LocalDateTime getDt() {
//		return dt;
//	}
//
//	public void setDt(LocalDateTime dt) {
//		this.dt = dt;
//	}

//	public String getServiceType() {
//		return serviceType;
//	}
//
//	public void setServiceType(String serviceType) {
//		this.serviceType = serviceType;
//	}
}


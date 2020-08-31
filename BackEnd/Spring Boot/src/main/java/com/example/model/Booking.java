package com.example.model;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


//@Table(name="BOOKINGS")
@Entity
public class Booking {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

	@Column
	private LocalDateTime dt;
	
	@Column(name="service_type")
	private String serviceType;
	
	
	@OneToOne
	private User client;
	
	@ManyToOne
	private Business business;
	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	private double bookingLength;
    private Date created_At;
    private Date updated_at;
	
	public Booking() {

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

	public User getClient() {
		return client;
	}

	public double getBookingLength() {
		return bookingLength;
	}
	
    public void setId(Long id) {
		this.id = id;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public void setBookingLength(double bookingLength) {
		this.bookingLength = bookingLength;
	}

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
    
	public LocalDateTime getDt() {
		return dt;
	}

	public void setDt(LocalDateTime dt) {
		this.dt = dt;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
}


package com.backend.product.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Booking")

public class Bookking {
	
	@Id
	private Long id;
	
	@Column
	private LocalDateTime dt;

	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="business_id")
	private Long businessId;
	
	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}
	
	@Column(name="service_type")
	private String serviceType;
	
	
	
	public Bookking() {}
	
	public Bookking(Long id,  Long userId, String serviceType) {
		this.id = id;
		
		this.userId = userId;
		this.serviceType = serviceType;
	}
		
	public Long getId() {
		return id;
	}
	
	
	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDt() {
		return dt;
	}

	public void setDt(LocalDateTime dt) {
		this.dt = dt;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getServiceType() {
		return serviceType;
	}
	
	public void setService(String serviceType) {
		this.serviceType = serviceType;
	}
}

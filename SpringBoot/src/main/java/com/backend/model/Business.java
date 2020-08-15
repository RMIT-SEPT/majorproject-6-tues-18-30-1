package com.backend.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Business {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String businessName;
	private List<String> employees;
	
	protected Business() {}

	public Business(String businessName) {
		super();
		this.businessName = businessName;
	}

	public Long getId() {
		return id;
	}

	public String getBusinessName() {
		return businessName;
	}

	public List<String> getEmployees() {
		return employees;
	}

}

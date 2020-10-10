package com.example.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

//@Table(name = "BUSINESSES")
@Entity
public class Business {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	private String name;
	private Date created_At;
	private Date updated_at;

	public Business() {
	}

	public Business(Long id) {
		this.id = id;
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

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

//	@ManyToOne
//	private List<String> employees;


	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@OneToMany
	private List<Service> services;

	@OneToMany
	private List<Worker> workers;
	
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

//	public List<String> getEmployees() {
//		return employees;
//	}

}
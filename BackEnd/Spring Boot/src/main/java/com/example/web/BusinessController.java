package com.example.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Booking;
import com.example.model.Business;
import com.example.services.BusinessService;

@RestController                     // Marks class as a controller
@RequestMapping("/api/Businesses")  
public class BusinessController {
	
	@Autowired
	private BusinessService businessService;
	
	@PostMapping("")
	public ResponseEntity<Business> createNewBooking(@RequestBody Business Business) {
        Business Business1 = BusinessService.saveOrUpdateBusiness(Business);
        return new ResponseEntity<Business>(Business, HttpStatus.CREATED);
    }
	
		
}

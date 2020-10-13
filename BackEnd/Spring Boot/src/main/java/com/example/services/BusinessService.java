package com.example.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.model.Business;
import com.example.repositories.BusinessRepository;

@Service
public class BusinessService {
	
	@Autowired
	private BusinessRepository businessRepository;

	public static Business saveOrUpdateBusiness(Business business) {
		return businessRepository.save(business);
	}
	
	

}

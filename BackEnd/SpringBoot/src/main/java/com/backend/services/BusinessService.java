package com.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.model.Business;
import com.backend.repositories.BusinessRepository;

import java.util.List;

@Service
public class BusinessService {

	@Autowired
	private BusinessRepository productRepository;

	public Business saveOrUpdatePerson(Business product) {

		// logic
		return productRepository.save(product);

	}

	public boolean putProduct() {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Business> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getProductOperatingHours(String productID) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getProductBusinessName(String productID) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean createNewProduct() {
		// TODO Auto-generated method stub
		return false;
	}
}

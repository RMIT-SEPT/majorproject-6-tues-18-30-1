package com.example.services;

import com.example.model.Business;
import com.example.repositories.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BusinessService {

    @Autowired
    private BusinessRepository businessRepository;

    public Business saveOrUpdateBusiness(Business business) {

        // business logic - add later
        return businessRepository.save(business);
    }

    // Returns all the business
    public ArrayList<Business> getBusinesses() {
        return (ArrayList<Business>) businessRepository.findAll();
    }

    // Returns the business if they exist or a null business
    public Optional<Business> getBusiness(Long id) {
        Optional<Business> business = businessRepository.findById(id);
        return business;
    }

    // Deletes if it exists
    public boolean deleteBusiness(Long id) {
        try {
            businessRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

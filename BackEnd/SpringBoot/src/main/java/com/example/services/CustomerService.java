package com.example.services;

import com.example.model.Customer;
import com.example.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveOrUpdateCustomer(Customer customer) {

        // business logic - add later
        return customerRepository.save(customer);
    }

    // Returns all the customers
    public ArrayList<Customer> getCustomers() {
        return (ArrayList<Customer>) customerRepository.findAll();
    }

    // Returns the customer if they exist or a null customer
    public Optional<Customer> getCustomer(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        return customer;
    }

    // Deletes if it exists
    public boolean deleteCustomer(Long id) {
        try {
            customerRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

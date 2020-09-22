package com.example.services;

import com.example.model.Service;
import com.example.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    public Service saveOrUpdateService(Service service) {

        // business logic - add later
        return serviceRepository.save(service);
    }

    // Returns all the services
    public ArrayList<Service> getServices() {
        return (ArrayList<Service>) serviceRepository.findAll();
    }

    // Returns the service if they exist or a null service
    public Optional<Service> getService(Long id) {
        Optional<Service> service = serviceRepository.findById(id);
        return service;
    }

    // Deletes if it exists
    public boolean deleteService(Long id) {
        try {
            serviceRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}

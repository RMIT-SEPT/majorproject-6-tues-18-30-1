package com.example.repositories;

import com.example.model.Booking;
import com.example.model.Service;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ServiceRepository extends CrudRepository<Service, Long> {
    public List<Service> findByBusinessId(Long businessId);
}

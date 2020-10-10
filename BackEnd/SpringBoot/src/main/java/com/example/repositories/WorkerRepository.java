package com.example.repositories;

import com.example.model.Service;
import com.example.model.Worker;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkerRepository extends CrudRepository<Worker, Long> {
    public List<Worker> findByBusinessId(Long businessId);
}

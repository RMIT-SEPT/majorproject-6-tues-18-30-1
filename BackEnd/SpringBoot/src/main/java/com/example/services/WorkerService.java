package com.example.services;

import com.example.model.Worker;
import com.example.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    public Worker saveOrUpdateWorker(Worker worker) {

        // business logic - add later
        return workerRepository.save(worker);
    }

    // Returns all the workers
    public ArrayList<Worker> getWorkers() {
        return (ArrayList<Worker>) workerRepository.findAll();
    }

    // Returns the worker if they exist or a null worker
    public Optional<Worker> getWorker(Long id) {
        Optional<Worker> worker = workerRepository.findById(id);
        return worker;
    }

    // Deletes if it exists
    public boolean deleteWorker(Long id) {
        try {
            workerRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }

}

package com.example.services;

import com.example.model.WorkingTime;
import com.example.repositories.WorkingTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class WorkingTimeService {

    @Autowired
    private WorkingTimeRepository workingTimeRepository;

    public WorkingTime saveOrUpdateWorkingTime(WorkingTime workingTime) {

        return workingTimeRepository.save(workingTime);
    }

    // Returns all the workingTime
    public ArrayList<WorkingTime> getWorkingTimes() {
        return (ArrayList<WorkingTime>) workingTimeRepository.findAll();
    }

    // Returns the workingTime if they exist or a null workingTime
    public Optional<WorkingTime> getWorkingTime(Long id) {
        Optional<WorkingTime> workingTime = workingTimeRepository.findById(id);
        return workingTime;
    }

    // Deletes if it exists
    public boolean deleteWorkingTime(Long id) {
        try {
            workingTimeRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            return false;
        }
        return true;
    }
}
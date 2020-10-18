package com.example.repositories;

import com.example.model.WorkingTime;
import org.springframework.data.repository.CrudRepository;

public interface WorkingTimeRepository extends CrudRepository<WorkingTime, Long> {
}

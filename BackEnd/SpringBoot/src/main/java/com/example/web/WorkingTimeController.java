package com.example.web;

import com.example.model.Worker;
import com.example.model.WorkingTime;
import com.example.services.WorkingTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/businesses/{businessId}/workers/{workerId}/workingTimes")
public class WorkingTimeController {

    @Autowired
    private WorkingTimeService workingTimeService;

    @PostMapping("")
    public ResponseEntity<WorkingTime> createNewWorkingTime(@RequestBody WorkingTime workingTime, @PathVariable Long businessId, @PathVariable Long workerId) {
        workingTime.setWorker(new Worker(workerId));
        WorkingTime workingTime1 = workingTimeService.saveOrUpdateWorkingTime(workingTime);
        return new ResponseEntity<WorkingTime>(workingTime, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<WorkingTime>> returnWorkingTimes(@PathVariable Long businessId, @PathVariable Long workerId) {
        ArrayList<WorkingTime> workingTimes = new ArrayList<WorkingTime>();
        workingTimes = workingTimeService.getWorkingTimes();
        return new ResponseEntity<ArrayList<WorkingTime>>(workingTimes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkingTime> getWorkingTime(@PathVariable Long id, @PathVariable Long businessId, @PathVariable Long workerId) {
        Optional<WorkingTime> workingTime = workingTimeService.getWorkingTime(id);
        if (workingTime.isPresent()) {
            return new ResponseEntity<WorkingTime>(workingTime.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    // Messes up the created/updated fields, do we want the id to have to be in the json or pulled from the @PathVariable?
    @PutMapping("/{id}")
    public ResponseEntity<WorkingTime> replaceWorkingTime(@RequestBody WorkingTime workingTime, @PathVariable Long id, @PathVariable Long businessId, @PathVariable Long workerId) {
        workingTime.setId(id);
        WorkingTime workingTime1 = workingTimeService.saveOrUpdateWorkingTime(workingTime);
        return new ResponseEntity<WorkingTime>(workingTime, HttpStatus.CREATED);
    }

    // Really need to think about how deletes should work, this is probably violating the database pretty hard
    @DeleteMapping("/{id}")
    public ResponseEntity deleteWorkingTime(@PathVariable Long id, @PathVariable Long businessId, @PathVariable Long workerId) {
        if (workingTimeService.deleteWorkingTime(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

package com.example.web;

import com.example.model.Business;
import com.example.model.Service;
import com.example.model.Worker;
import com.example.model.WorkingTime;
import com.example.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/businesses/{businessId}/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @PostMapping("")                // What to do with a POST request
    public ResponseEntity<?> createNewWorker(@Valid @RequestBody Worker worker, @PathVariable Long businessId, BindingResult result) {
        ResponseEntity<?> errorList = returnErrors(result);
        if (errorList != null) return errorList;
        worker.setBusiness(new Business(businessId));
        Worker worker1 = workerService.saveOrUpdateWorker(worker);
        return new ResponseEntity<Worker>(worker, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/add-workHours/{workingTimeId}")
    public ResponseEntity<Worker> addWorkHours(@PathVariable Long businessId, @PathVariable Long workingTimeId, @PathVariable Long id) {

        Optional<Worker> worker = workerService.getWorker(id);
        if (!worker.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        worker.get().addWorkingTime(new WorkingTime(workingTimeId));
        Worker worker1 = workerService.saveOrUpdateWorker(worker.get());
        return new ResponseEntity<Worker>(worker.get(), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<Worker>> returnWorkers(@PathVariable Long businessId) {
        ArrayList<Worker> workers = new ArrayList<Worker>();
        workers = workerService.getWorkers(businessId);
        return new ResponseEntity<ArrayList<Worker>>(workers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorker(@PathVariable Long id, @PathVariable Long businessId) {
        Optional<Worker> worker = workerService.getWorker(id);
        if (worker.isPresent()) {
            return new ResponseEntity<Worker>(worker.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    // Messes up the created/updated fields, do we want the id to have to be in the json or pulled from the @PathVariable?
    @PutMapping("/{id}")
    public ResponseEntity<?> replaceWorker(@Valid @RequestBody Worker worker, @PathVariable Long id, @PathVariable Long businessId, BindingResult result) {
        ResponseEntity<?> errorList = returnErrors(result);
        if (errorList != null) return errorList;
        worker.setId(id);
        Worker worker1 = workerService.saveOrUpdateWorker(worker);
        return new ResponseEntity<Worker>(worker, HttpStatus.CREATED);
    }

    // Really need to think about how deletes should work, this is probably violating the database pretty hard
    @DeleteMapping("/{id}")
    public ResponseEntity deleteWorker(@PathVariable Long id, @PathVariable Long businessId) {
        if (workerService.deleteWorker(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<?> returnErrors(BindingResult result) {
        if (result.hasErrors()) {
            ArrayList<String> errorList = new ArrayList<>();
            for (FieldError error: result.getFieldErrors()) {
                errorList.add(error.toString());
            }
            return new ResponseEntity<List<String>>(errorList, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

}

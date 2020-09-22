package com.example.web;

import com.example.model.Worker;
import com.example.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/workers")
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @PostMapping("")                // What to do with a POST request
    public ResponseEntity<Worker> createNewWorker(@RequestBody Worker worker) {
        Worker worker1 = workerService.saveOrUpdateWorker(worker);
        return new ResponseEntity<Worker>(worker, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<Worker>> returnWorkers() {
        ArrayList<Worker> workers = new ArrayList<Worker>();
        workers = workerService.getWorkers();
        return new ResponseEntity<ArrayList<Worker>>(workers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Worker> getWorker(@PathVariable Long id) {
        Optional<Worker> worker = workerService.getWorker(id);
        if (worker.isPresent()) {
            return new ResponseEntity<Worker>(worker.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    // Messes up the created/updated fields, do we want the id to have to be in the json or pulled from the @PathVariable?
    @PutMapping("/{id}")
    public ResponseEntity<Worker> replaceWorker(@RequestBody Worker worker, @PathVariable Long id) {
        worker.setId(id);
        Worker worker1 = workerService.saveOrUpdateWorker(worker);
        return new ResponseEntity<Worker>(worker, HttpStatus.CREATED);
    }

    // Really need to think about how deletes should work, this is probably violating the database pretty hard
    @DeleteMapping("/{id}")
    public ResponseEntity deleteWorker(@PathVariable Long id) {
        if (workerService.deleteWorker(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

}

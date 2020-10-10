package com.example.web;

import com.example.model.Service;
import com.example.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/services")
public class ServiceController {

    // CRUD operations for services

    @Autowired
    private ServiceService serviceService;

    @PostMapping("")
    public ResponseEntity<Service> createNewService(@RequestBody Service service) {
        Service service1 = serviceService.saveOrUpdateService(service);
        return new ResponseEntity<Service>(service, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<Service>> returnServices() {
        ArrayList<Service> services = new ArrayList<Service>();
        services = serviceService.getServices();
        return new ResponseEntity<ArrayList<Service>>(services, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getService(@PathVariable Long id) {
        Optional<Service> service = serviceService.getService(id);
        if (service.isPresent()) {
            return new ResponseEntity<Service>(service.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Service> replaceService(@RequestBody Service service, @PathVariable Long id) {
        service.setId(id);
        Service service1 = serviceService.saveOrUpdateService(service);
        return new ResponseEntity<Service>(service, HttpStatus.CREATED);
    }

    // Really need to think about how deletes should work, this is probably violating the database pretty hard
    @DeleteMapping("/{id}")
    public ResponseEntity deleteService(@PathVariable Long id) {
        if (serviceService.deleteService(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    // Functional services that are essential sub read tasks
    @GetMapping("/{id}/slots")
    public ResponseEntity getSlots(@PathVariable Long id) {
        Optional<Service> service = serviceService.getService(id);
        if (!service.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        // Making slots given a start time and a service length.
        Service service1 = service.get();
        LocalTime time = service1.getStartTime();
        ArrayList<LocalTime> slotTimes = new ArrayList<LocalTime>();
        while (time.isBefore(service1.getEndTime().minus(service1.getLength(), ChronoUnit.MINUTES)) || time.equals(service1.getEndTime().minus(service1.getLength(), ChronoUnit.MINUTES))) {
            slotTimes.add(time);
            time = time.plusMinutes(service1.getLength());
        }
        return new ResponseEntity<ArrayList<LocalTime>> (slotTimes, HttpStatus.OK);
    }
}

package com.example.web;

import com.example.model.*;
import com.example.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/businesses/{businessId}/services")
public class ServiceController {

    // CRUD operations for services

    @Autowired
    private ServiceService serviceService;

    @PostMapping("")
    public ResponseEntity<Service> createNewService(@RequestBody Service service, @PathVariable Long businessId) {
        service.setBusiness(new Business(businessId));
        Service service1 = serviceService.saveOrUpdateService(service);
        return new ResponseEntity<Service>(service, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/add-worker/{workerId}")
    public ResponseEntity<Service> addWorker(@PathVariable Long businessId, @PathVariable Long workerId, @PathVariable Long id) {

        Optional<Service> service = serviceService.getService(id);
        if (!service.isPresent()) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        service.get().addWorker(new Worker(workerId));
        Service service1 = serviceService.saveOrUpdateService(service.get());
        return new ResponseEntity<Service>(service.get(), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<Service>> returnServices(@PathVariable Long businessId) {
        ArrayList<Service> services = new ArrayList<Service>();
        services = serviceService.getServices(businessId);
        return new ResponseEntity<ArrayList<Service>>(services, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getService(@PathVariable Long id, @PathVariable Long businessId) {
        Optional<Service> service = serviceService.getService(id);
        if (service.isPresent()) {
            return new ResponseEntity<Service>(service.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Service> replaceService(@RequestBody Service service, @PathVariable Long id, @PathVariable Long businessId) {
        service.setId(id);
        Service service1 = serviceService.saveOrUpdateService(service);
        return new ResponseEntity<Service>(service, HttpStatus.CREATED);
    }

    // Really need to think about how deletes should work, this is probably violating the database pretty hard
    @DeleteMapping("/{id}")
    public ResponseEntity deleteService(@PathVariable Long id, @PathVariable Long businessId) {
        if (serviceService.deleteService(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    // Functional services that are essential sub read tasks

//    @GetMapping("/{id}/slots")
//    public ResponseEntity getSlots(@PathVariable Long id, @PathVariable Long businessId) {
//        Optional<Service> service = serviceService.getService(id);
//        if (!service.isPresent()) {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        // Making slots given a start time and a service length.
//        Service service1 = service.get();
//        LocalTime time = service1.getStartTime();
//        ArrayList<LocalTime> slotTimes = new ArrayList<LocalTime>();
//        while (time.isBefore(service1.getEndTime().minus(service1.getLength(), ChronoUnit.MINUTES)) || time.equals(service1.getEndTime().minus(service1.getLength(), ChronoUnit.MINUTES))) {
//            slotTimes.add(time);
//            time = time.plusMinutes(service1.getLength());
//        }
//        return new ResponseEntity<ArrayList<LocalTime>> (slotTimes, HttpStatus.OK);
//    }

    @GetMapping("/{id}/booking-slots/{date}")
    public ResponseEntity getAvailableBookings(@PathVariable Long id, @PathVariable Long businessId, @PathVariable String date) {
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

        LocalDate bookingDate = LocalDate.parse(date);

        System.out.println(bookingDate);

        ArrayList<Booking> possibleBookings = new ArrayList<Booking>();

        List<Worker> workers = service1.getWorkers();
        // For each worker related to the service, check if they are working that day and are free
        for (Worker worker : workers) {
            System.out.println(worker);
            System.out.println("The workers");
            List<WorkingTime> workingTimes = worker.getWorkingTimes();
            for (WorkingTime workingTime : workingTimes) {
                System.out.println(workingTime);
                System.out.println("The workingTime");
                System.out.println(workingTime);
                if (workingTime.getDate().equals(bookingDate)) {
                    System.out.println("The dates match");
                   for (LocalTime slot : slotTimes) {
                       System.out.println(slot);
                       if ((slot.isAfter(workingTime.getStartTime()) || slot.equals(workingTime.getStartTime())) && (slot.plus(service1.getLength(), ChronoUnit.MINUTES).isBefore(workingTime.getEndTime()) || (slot.plus(service1.getLength(), ChronoUnit.MINUTES).equals(workingTime.getEndTime())))) {
                           possibleBookings.add(new Booking(LocalDateTime.of(bookingDate, slot), service1, worker));
                       }
                   }
                }
            }
        }

        return new ResponseEntity<ArrayList<Booking>> (possibleBookings, HttpStatus.OK);
    }
}

package com.example.web;

import com.example.model.Business;
import com.example.services.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController                     
@RequestMapping("/api/businesses")
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    @PostMapping("")                // What to do with a POST request
    public ResponseEntity<Business> createNewBusiness(@RequestBody Business business) {
        Business business1 = businessService.saveOrUpdateBusiness(business);
        return new ResponseEntity<Business>(business, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<Business>> returnBusinesses() {
        ArrayList<Business> businesses = new ArrayList<Business>();
        businesses = businessService.getBusinesses();
        return new ResponseEntity<ArrayList<Business>>(businesses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Business> getBusiness(@PathVariable Long id) {
        Optional<Business> business = businessService.getBusiness(id);
        if (business.isPresent()) {
            return new ResponseEntity<Business>(business.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    // Messes up the created/updated fields, do we want the id to have to be in the json or pulled from the @PathVariable?
    @PutMapping("/{id}")
    public ResponseEntity<Business> replaceBusiness(@RequestBody Business business, @PathVariable Long id) {
        business.setId(id);
        Business business1 = businessService.saveOrUpdateBusiness(business);
        return new ResponseEntity<Business>(business, HttpStatus.CREATED);
    }

    // Really need to think about how deletes should work, this is probably violating the database pretty hard
    @DeleteMapping("/{id}")
    public ResponseEntity deleteBusiness(@PathVariable Long id) {
        if (businessService.deleteBusiness(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

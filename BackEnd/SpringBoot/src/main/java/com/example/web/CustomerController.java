package com.example.web;

import com.example.model.Customer;
import com.example.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController                     // Marks class as a controller
@RequestMapping("/api/customers")       // The URI that this controller is connected to
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("")                // What to do with a POST request
    public ResponseEntity<?> createNewCustomer(@Valid @RequestBody Customer customer, BindingResult result) {
        ResponseEntity<?> errorList = returnErrors(result);
        if (errorList != null) return errorList;
        Customer customer1 = customerService.saveOrUpdateCustomer(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<Customer>> returnCustomers() {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        customers = customerService.getCustomers();
        return new ResponseEntity<ArrayList<Customer>>(customers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomer(id);
        if (customer.isPresent()) {
            return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    // Messes up the created/updated fields, do we want the id to have to be in the json or pulled from the @PathVariable?
    @PutMapping("/{id}")
    public ResponseEntity<?> replaceCustomer(@Valid @RequestBody Customer customer, @PathVariable Long id, BindingResult result) {
        ResponseEntity<?> errorList = returnErrors(result);
        if (errorList != null) return errorList;
        customer.setId(id);
        Customer customer1 = customerService.saveOrUpdateCustomer(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
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

    // Really need to think about how deletes should work, this is probably violating the database pretty hard
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        if (customerService.deleteCustomer(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

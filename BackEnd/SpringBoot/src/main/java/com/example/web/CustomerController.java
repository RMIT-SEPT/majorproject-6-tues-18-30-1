package com.example.web;

import com.example.model.Customer;
import com.example.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@CrossOrigin
@RestController                     // Marks class as a controller
@RequestMapping("/api/customers")       // The URI that this controller is connected to
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("")                // What to do with a POST request
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer) {
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
    public ResponseEntity<Customer> getcustomer(@PathVariable Long id) {
        Optional<Customer> customer = customerService.getCustomer(id);
        if (customer.isPresent()) {
            return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    // Messes up the created/updated fields, do we want the id to have to be in the json or pulled from the @PathVariable?
    @PutMapping("/{id}")
    public ResponseEntity<Customer> replaceCustomer(@RequestBody Customer customer, @PathVariable Long id) {
        customer.setId(id);
        Customer customer1 = customerService.saveOrUpdateCustomer(customer);
        return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
    }

    // Patch is long, but I like it better than put :/
//    @PatchMapping("/{id}")
//    public ResponseEntity<User> updateUser(@RequestBody User updatedUser, @PathVariable Long id) {
//        Optional<User> storedUser = userService.getUser(id);
//        if (!storedUser.isPresent()) {
//            return new ResponseEntity(HttpStatus.NOT_FOUND);
//        }
//        if (updatedUser.getFirstName() != null) {
//            storedUser.get().setFirstName(updatedUser.getFirstName());
//        }
//        if (updatedUser.getLastName() != null) {
//            storedUser.get().setLastName(updatedUser.getLastName());
//        }
//
//        User user1 = userService.saveOrUpdateUser(storedUser.get());
//        return new ResponseEntity<User>(storedUser.get(), HttpStatus.CREATED);
//    }

    // Really need to think about how deletes should work, this is probably violating the database pretty hard
    @DeleteMapping("/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Long id) {
        if (customerService.deleteCustomer(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

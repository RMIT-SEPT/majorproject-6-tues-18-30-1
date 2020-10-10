package com.example;

import com.example.model.Customer;
import com.example.services.CustomerService;
import com.example.web.BookingController;
import com.example.web.CustomerController;
import com.example.web.ServiceController;
import com.example.web.WorkerController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CustomerService customerService;

    @BeforeTransaction
    public void setup() {

    }
    // Test that get request works to get all customers
    @Test
    @Transactional
    public void getCustomers() throws Exception {
        Customer customer1 = new Customer("John", "Doe", "0403123123", "test1@example.com");
        Customer customer2 = new Customer("Jane", "Doe", "0403123124", "test2@example.com");
        entityManager.persist(customer1);
        entityManager.persist(customer2);
        this.mockMvc.perform(get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("John")))
                .andExpect(content().string(containsString("Jane")));
    }
    // Test that can get a single customer
    @Test
    @Transactional
    public void getCustomer() throws Exception {
        Customer customer1 = new Customer("John", "Doe", "0403123123", "test1@example.com");
        Customer customer2 = new Customer("Jane", "Doe", "0403123124", "test2@example.com");
        entityManager.persist(customer1);
        entityManager.persist(customer2);
        this.mockMvc.perform(get("/api/customers/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("John")));
    }
    // Test that we can modify a customer with put
    @Test
    @Transactional
    public void editCustomer() throws Exception {
        Customer customer1 = new Customer("John", "Doe", "0403123123", "test1@example.com");
        entityManager.persist(customer1);
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/api/customers/1")
                .content(asJsonString(new Customer("Jackson", "Doe", "0403123123", "test1@example.com")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Jackson")));
    }

//    @Test
//    public void testService() {
//        Customer customer1 = new Customer("John", "Doe", "0403123123", "test1@example.com");
//        customerService.saveOrUpdateCustomer(customer1);
//    }

    // helper function
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

package com.example;

import com.example.model.Business;
import com.example.model.Worker;
import com.example.services.WorkerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.hamcrest.Matchers.containsString;

@SpringBootTest
@AutoConfigureMockMvc
public class WorkerTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private WorkerService workerService;

    @BeforeTransaction
    public void setup() {

    }
    // ===========================
    // Testing correct inputs work
    //============================

    // Test that we can modify a worker with put
    @Test
    @Transactional
    public void editWorker() throws Exception {
        Business business1 = new Business();
        entityManager.persist(business1);
        Worker worker1 = new Worker("Bob", "Smith", "0403123123", "test1@example.com", "Hi");
        entityManager.persist(worker1);
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/api/businesses/1/workers/1")
                .content(asJsonString(new Worker("Jackson", "Smith", "0403123123", "test1@example.com", "Hi")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString("Jackson")));
    }

    // ====================================
    // Testing incorrect inputs sends errors
    //=====================================

    // Test that we can get a single worker
    @Test
    @Transactional
    public void getNonExistentWorker() throws Exception {
        Business business1 = new Business();
        entityManager.persist(business1);
        Worker worker1 = new Worker("Bob", "Smith", "0403123123", "test1@example.com", "Hi");
        entityManager.persist(worker1);
        this.mockMvc.perform(get("/api/businesses/1/workers/10"))
                .andExpect(status().isNotFound());
    }

    // add worker with empty name
    @Test
    @Transactional
    public void addWorkerWithEmptyFirstName() throws Exception {
        Business business1 = new Business();
        entityManager.persist(business1);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/businesses/1/workers")
                .content(asJsonString(new Worker("", "Bob", "0403123123", "test1@example.com", "Hi")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    // add worker with invalid email
    @Test
    @Transactional
    public void addWorkerWithInvalidEmail() throws Exception {
        Business business1 = new Business();
        entityManager.persist(business1);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/businesses/1/workers")
                .content(asJsonString(new Worker("", "Bob", "0403123123", "test1examplecom", "Hi")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    // add worker to post without any fields
    @Test
    @Transactional
    public void addEmptyWorker() throws Exception {
        Business business1 = new Business();
        entityManager.persist(business1);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/businesses/1/workers")
                .content("")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    // add worker with invalid phonenumber
    @Test
    @Transactional
    public void addWorkerWithInvalidPhone() throws Exception {
        Business business1 = new Business();
        entityManager.persist(business1);
        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/api/businesses/1/workers")
                .content(asJsonString(new Worker("", "Bob", "1", "test1examplecom", "Hi")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    // edit worker with invalid name
    @Test
    @Transactional
    public void editWorkerWithInvalidName() throws Exception {
        Business business1 = new Business();
        entityManager.persist(business1);
        Worker worker1 = new Worker("Bob", "Smith", "0403123123", "test1@example.com", "Hi");
        entityManager.persist(worker1);
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/api/businesses/1/workers/1")
                .content(asJsonString(new Worker("J", "Smith", "0403123123", "test1@example.com", "Hi")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    // edit worker with invalid phone number
    @Test
    @Transactional
    public void editWorkerWithInvalidNumber() throws Exception {
        Business business1 = new Business();
        entityManager.persist(business1);
        Worker worker1 = new Worker("Bob", "Smith", "0403123123", "test1@example.com", "Hi");
        entityManager.persist(worker1);
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/api/businesses/1/workers/1")
                .content(asJsonString(new Worker("J", "Smith", "0", "test1@example.com", "Hi")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
    // edit worker with invalid email
    @Test
    @Transactional
    public void editWorkerWithInvalidEmail() throws Exception {
        Business business1 = new Business();
        entityManager.persist(business1);
        Worker worker1 = new Worker("Bob", "Smith", "0403123123", "test1@example.com", "Hi");
        entityManager.persist(worker1);
        this.mockMvc.perform(MockMvcRequestBuilders
                .put("/api/businesses/1/workers/1")
                .content(asJsonString(new Worker("J", "Smith", "0", "test1example.com", "Hi")))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    // helper function
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

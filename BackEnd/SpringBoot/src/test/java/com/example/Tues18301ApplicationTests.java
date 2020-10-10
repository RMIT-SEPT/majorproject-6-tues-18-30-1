package com.example;

import com.example.model.Customer;
import com.example.web.BookingController;
import com.example.web.CustomerController;
import com.example.web.ServiceController;
import com.example.web.WorkerController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class Tues18301ApplicationTests {

	@Test
	void applicationRuns() {
	}

	@Autowired
	private CustomerController customerController;
	@Autowired
	private BookingController bookingController;
	@Autowired
	private ServiceController serviceController;
	@Autowired
	private WorkerController workerController;

	@Test
	public void customerControllerExists() throws Exception {
		assertThat(customerController).isNotNull();
	}
	@Test
	public void bookingControllerExists() throws Exception {
		assertThat(bookingController).isNotNull();
	}
	@Test
	public void serviceControllerExists() throws Exception {
		assertThat(serviceController).isNotNull();
	}
	@Test
	public void workerControllerExists() throws Exception {
		assertThat(workerController).isNotNull();
	}


	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void createValidCustomer() throws Exception {
		Customer customer = new Customer("John", "Doe", "0403123123", "test@example.com");
		String customerJson = objectMapper.writeValueAsString(customer);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(customerJson, headers);

		assertThat(this.restTemplate.postForObject("http://localhost:"+port+"/api/customers", entity, String.class)).contains("John");
	}

}

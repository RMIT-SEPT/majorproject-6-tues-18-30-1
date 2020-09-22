package com.example;

import com.example.web.BookingController;
import com.example.web.CustomerController;
import com.example.web.ServiceController;
import com.example.web.WorkerController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class Tues18301ApplicationTests {

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

}

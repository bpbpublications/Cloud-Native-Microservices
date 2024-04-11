package com.fsm.customerenquiry;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.fsm.customerenquiry.entity.Customer;
import com.fsm.customerenquiry.repository.CustomerRepository;
import com.fsm.customerenquiry.service.CustomerService;

@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
public class CustomerServiceTests {

	@TestConfiguration
	static class CustomerServiceTestContextConfiguration {
		@Bean
		public CustomerService customerService() {
			return new CustomerService();
		}
	}

	@Autowired
	private CustomerService customerService;

	@MockBean
	private CustomerRepository customerRepository;

	@BeforeEach
	public void setUp() {

		List<Customer> customerList = new ArrayList<>();
		customerList.add(new Customer(1));
		customerList.add(new Customer(2));

		Mockito.when(customerRepository.findAll()).thenReturn(customerList);
	}

	@Test
	public void customersShouldBeFound() {
		List<Customer> customerList = customerService.getCustomers();
		assertThat(customerList.size()).isEqualTo(2);
	}
}
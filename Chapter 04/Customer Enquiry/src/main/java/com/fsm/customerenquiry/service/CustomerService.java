package com.fsm.customerenquiry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsm.customerenquiry.entity.Customer;
import com.fsm.customerenquiry.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository customerRepository;

	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}
}
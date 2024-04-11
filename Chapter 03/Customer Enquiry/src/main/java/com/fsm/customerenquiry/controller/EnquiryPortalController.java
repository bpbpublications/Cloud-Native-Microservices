package com.fsm.customerenquiry.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fsm.customerenquiry.entity.Customer;
import com.fsm.customerenquiry.service.CustomerService;
import com.fsm.customerenquiry.service.EnquiryService;

@RestController
@RequestMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
public class EnquiryPortalController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private EnquiryService enquiryService;

	@GetMapping(path = "/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}

	@GetMapping(path = "/fproducts")
	public List<JsonNode> getFinancialProducts() {
		return enquiryService.getFinancialProducts().stream()
				.map(product -> JsonNodeFactory.instance.objectNode().put("name", product.name())
						.put("code", product.getCode()).put("description", product.getDescription()))
				.collect(Collectors.toList());
	}

	@PostMapping(path = "/enquiry")
	public boolean enquiry(@RequestParam("customerId") int customerId,
			@RequestParam("financialProductName") String financialProductName) {
		try {
			enquiryService.customerEnquiry(customerId, financialProductName);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@GetMapping(path = "/config")
	public String getConfigurations() {
		return JsonNodeFactory.instance.objectNode()
				.put("enquiry.broadcast.enabled", enquiryService.getBroadcastEnabled())
				.put("enquiry.broadcast.baseurl", enquiryService.getBroadcastBaseUrl()).toString();
	}
}
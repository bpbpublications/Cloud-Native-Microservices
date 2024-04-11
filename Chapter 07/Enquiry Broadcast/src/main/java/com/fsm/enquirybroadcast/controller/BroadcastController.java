package com.fsm.enquirybroadcast.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.RequestParam;
	import org.springframework.web.bind.annotation.RestController;
	
	import com.fsm.enquirybroadcast.service.BroadcastService;
	
	@RestController
	@RequestMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public class BroadcastController {
	
	 @Autowired
	 BroadcastService broadcastService;
	
	 @PostMapping(path = "/broadcast")
	 public boolean enquiryBroadcast(@RequestParam("customerId") int customerId,
	   @RequestParam("financialProduct") String financialProduct,
	   @RequestParam("requestDate") @DateTimeFormat(pattern = "yyyyMMdd") Date requestDate) {
	
	  return broadcastService.broadcast(customerId, financialProduct, requestDate);
	
	 }
	}
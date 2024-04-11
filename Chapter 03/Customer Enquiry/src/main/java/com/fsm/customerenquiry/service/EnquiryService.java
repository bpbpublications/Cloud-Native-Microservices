package com.fsm.customerenquiry.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fsm.customerenquiry.FinancialProduct;
import com.fsm.customerenquiry.entity.Customer;
import com.fsm.customerenquiry.entity.Enquiry;
import com.fsm.customerenquiry.repository.EnquiryRepository;

@Service
@RefreshScope
public class EnquiryService {

	@Value("${enquiry.broadcast.enabled}")
	String broadcastEnabled;

	@Value("${enquiry.broadcast.baseurl}")
	String broadcastBaseUrl;

	@Autowired
	EnquiryRepository enquiryRepository;

	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

	public void customerEnquiry(int customerId, String financialProductName) {
		Date requestDate = Calendar.getInstance().getTime();
		persistEnquiry(customerId, financialProductName, requestDate);

		if ("Y".equalsIgnoreCase(broadcastEnabled)) {
			broadcastEnquiry(customerId, financialProductName, requestDate);
		}

	}

	public List<FinancialProduct> getFinancialProducts() {
		return FinancialProduct.getFinancialProducts();
	}

	private void persistEnquiry(int customerId, String financialProductName, Date requestDate) {
		Enquiry enquiry = new Enquiry();
		Customer customer = new Customer(customerId);
		enquiry.setCustomer(customer);
		enquiry.setEnquiryAbout(FinancialProduct.getFinancialProductFromName(financialProductName).getCode());
		enquiry.setCreatedBy("system");
		enquiry.setCreatedDate(requestDate);

		enquiryRepository.save(enquiry);
	}

	private void broadcastEnquiry(int customerId, String financialProductName, Date requestDate) {

		WebClient client = WebClient.builder().baseUrl(broadcastBaseUrl)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

		try {
			boolean success = client.post()
					.uri(uriBuilder -> uriBuilder.path("/broadcast/").queryParam("customerId", customerId)
							.queryParam("financialProduct", financialProductName)
							.queryParam("requestDate", DATE_FORMAT.format(requestDate)).build())
					.retrieve().bodyToMono(Boolean.class).block();

			if (success)
				System.out.println("Enquiry has been sent for broadcasting");

		} catch (Exception e) {
			System.out.println("Enquiry has not been sent for broadcasting");
			e.printStackTrace();
			throw e;
		}
	}

	public String getBroadcastEnabled() {
		return broadcastEnabled;
	}

	public String getBroadcastBaseUrl() {
		return broadcastBaseUrl;
	}

}
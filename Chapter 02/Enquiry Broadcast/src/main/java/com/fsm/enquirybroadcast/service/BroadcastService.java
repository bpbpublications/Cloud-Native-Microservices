package com.fsm.enquirybroadcast.service;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.fsm.enquirybroadcast.entity.FailedRequestS3;
import com.fsm.enquirybroadcast.repository.FailedRequestS3Repository;

@Service
public class BroadcastService {

	public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

	@Autowired
	private AmazonS3 s3Client;

	@Autowired
	private FailedRequestS3Repository failedRequestS3Repository;

	private String bucketName = "fs-marketplace-bucket";

	// Map maintains financial product codes corresponding to their names
	private Map<String, String> financialProductMap;

	public boolean broadcast(int customerId, String financialProduct, Date requestDate) {
		boolean isSuccess = true;

		if (!BroadcastToS3(customerId, financialProduct, requestDate))
			isSuccess = false;

		if (!BroadcastToTopic(customerId, financialProduct, requestDate))
			isSuccess = false;

		if (!isSuccess) // Persist the request in DB for retry
			persistRequest(customerId, financialProduct, requestDate);

		return isSuccess;
	}

	private boolean BroadcastToS3(int customerId, String financialProduct, Date requestDate) {

		try {

			// File is created in a requested date folder
			// File name is financial product requested appended with current time
			String fileName = DATE_FORMAT.format(requestDate) + "/" + financialProduct + "-" + LocalTime.now() + ".txt";

			// Content of file is customer id '|' separated with financial product enquired
			String data = "CustomerID:" + customerId + "|FinancialProduct:" + financialProduct;

			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(data.length());

			s3Client.putObject(bucketName, fileName, new ByteArrayInputStream(data.getBytes()), meta);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private boolean BroadcastToTopic(int customerId, String financialProduct, Date requestDate) {
		return true;
	}

	private void persistRequest(int customerId, String financialProduct, Date requestDate) {

		if (financialProductMap == null) {
			financialProductMap = new HashMap<>();
			populateFinanicalProducts(financialProductMap);
		}

		FailedRequestS3 failedRequestS3 = new FailedRequestS3();
		failedRequestS3.setFailedRequestS3CustomerId(customerId);
		failedRequestS3.setFailedRequestS3EnquiryAbout(financialProductMap.get(financialProduct));
		failedRequestS3.setFailedRequestS3Date(requestDate);
		failedRequestS3.setCreatedDate(Calendar.getInstance().getTime());

		failedRequestS3Repository.save(failedRequestS3);
	}

	private void populateFinanicalProducts(Map<String, String> financialProductMap) {

		WebClient client = WebClient.builder().baseUrl("http://localhost:8080")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

		try {
			List<Map<String, String>> financialProducts = client.get()
					.uri(uriBuilder -> uriBuilder.path("/fproducts/").build()).retrieve().bodyToMono(List.class)
					.block();

			for (Map<String, String> fpMap : financialProducts) {
				String name = null;
				String code = null;
				for (Entry<String, String> entry : fpMap.entrySet()) {
					if ("name".equals(entry.getKey())) {
						name = entry.getValue();
					} else if ("code".equals(entry.getKey())) {
						code = entry.getValue();
					}
				}
				financialProductMap.put(name, code);
			}
		} catch (Exception e) {
			System.out.println("Failed loading financial products");
			e.printStackTrace();
			throw e;
		}
	}

}
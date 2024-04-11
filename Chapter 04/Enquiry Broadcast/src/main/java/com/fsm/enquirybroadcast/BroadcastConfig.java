package com.fsm.enquirybroadcast;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
	import com.amazonaws.services.s3.AmazonS3;
	import com.amazonaws.services.s3.AmazonS3ClientBuilder;
	
	@Configuration
	public class BroadcastConfig {
	
	 @Bean
	 public AmazonS3 AmazonS3() {
	
	  AWSCredentials credentials = new BasicAWSCredentials("<access key>","<secret key>");
	
	  AmazonS3 s3client = AmazonS3ClientBuilder.standard()
	    .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1).build();
	
	  return s3client;
	 }
	}
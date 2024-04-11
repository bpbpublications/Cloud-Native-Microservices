package com.fsm.enquirybroadcast;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import java.io.InputStream;
import java.util.Calendar;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.fsm.enquirybroadcast.service.BroadcastService;

@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
public class BroadcastServiceTests {

	@TestConfiguration
	static class EnquiryServiceTestContextConfiguration {
		@Bean
		public BroadcastService broadcastService() {
			return new BroadcastService();
		}
	}

	@Autowired
	private BroadcastService broadcastService;

	@MockBean
	private AmazonS3 s3Client;

	@BeforeEach
	public void setUp() {
		Mockito.when(s3Client.putObject(any(String.class), any(String.class), any(InputStream.class),
				any(ObjectMetadata.class))).thenThrow(RuntimeException.class);
	}

	@Test
	public void whenAWSServiceThrowsException_thenFalseShouldBeReturned() {
		assertThat(broadcastService.broadcast(1, "HOME_LOAN", Calendar.getInstance().getTime())).isEqualTo(false);
	}
}

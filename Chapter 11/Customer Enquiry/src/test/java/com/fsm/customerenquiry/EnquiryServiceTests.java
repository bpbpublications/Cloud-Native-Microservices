package com.fsm.customerenquiry;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import com.fsm.customerenquiry.entity.Enquiry;
import com.fsm.customerenquiry.repository.EnquiryRepository;
import com.fsm.customerenquiry.service.EnquiryService;

@SpringBootTest(properties = "spring.main.allow-bean-definition-overriding=true")
public class EnquiryServiceTests {

	@TestConfiguration
	static class EnquiryServiceTestContextConfiguration {
		@Bean
		public EnquiryService enquiryService() {
			return new EnquiryService();
		}
	}

	@Autowired
	private EnquiryService enquiryService;

	@MockBean
	private EnquiryRepository enquiryRepository;

	@BeforeEach
	public void setUp() {
		Mockito.when(enquiryRepository.save(any(Enquiry.class))).thenReturn(null);
	}

	@Test
	public void whenInvalidFinancialProductCode_thenEnquiryShouldNotBeSaved() {
		assertThrows(RuntimeException.class, () -> enquiryService.customerEnquiry(1, "XXXX"));
	}
}

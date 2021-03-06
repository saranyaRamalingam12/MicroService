package com.orderhistory;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OrderHistoryAndPaymentApplication {

	private static final Logger LOGGER=LoggerFactory.getLogger(OrderHistoryAndPaymentApplication.class);
	
	@Bean 
	public ModelMapper modelMapper() {
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper;
	}
	
	 @Bean
	   public RestTemplate getRestTemplate() {
	      return new RestTemplate();
	   }

	public static void main(String[] args) {
		SpringApplication.run(OrderHistoryAndPaymentApplication.class, args);
		LOGGER.info("In OrderHistory Application");
	}

}

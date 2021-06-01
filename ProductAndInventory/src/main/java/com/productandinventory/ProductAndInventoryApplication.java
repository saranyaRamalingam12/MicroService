package com.productandinventory;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProductAndInventoryApplication {

	 private static final Logger LOGGER=LoggerFactory.getLogger(ProductAndInventoryApplication.class);
	@Bean 
	public ModelMapper modelMapper() {
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProductAndInventoryApplication.class, args);
		LOGGER.info("In Product and Inventory Application");
	}

}

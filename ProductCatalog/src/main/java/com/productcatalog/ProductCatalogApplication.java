package com.productcatalog;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication

public class ProductCatalogApplication {

	 private static final Logger LOGGER=LoggerFactory.getLogger(ProductCatalogApplication.class);
	 
	@Bean 
	public ModelMapper modelMapper() {
		ModelMapper modelMapper=new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
		return modelMapper;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ProductCatalogApplication.class, args);
		LOGGER.info("In Product Catalog");
	}
	
}

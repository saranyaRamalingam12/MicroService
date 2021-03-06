package com.cart.client;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cart.dto.ProductDTO;
import com.cart.exceptionhandling.DataReceivedErrorException;

@Service
public class ProductServiceImpl implements ProductService 
{
	@Autowired	RestTemplate restTemplate;
	@Autowired   ProductDTO productDTO;
	private static final Logger LOGGER=LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Override
	public ProductDTO getProductById(Long productId) {
		LOGGER.info("Service Call to Product Service to get Product");
		String url="http://localhost:8082/api/order/Product/editProduct/{id}";
		ResponseEntity<ProductDTO> productDto=  restTemplate.getForEntity(url, ProductDTO.class,productId);
		if(productDto.getStatusCode()!=HttpStatus.OK){
			throw new DataReceivedErrorException("Error in finding the cartId from Cart Service"+productId);
		}
		return productDto.getBody();
	}

	@Override
	public void productBlock(int bookedQuantity, Long productId) {
		LOGGER.info("Service Call to Product Service to block Product ");
		String url="http://localhost:8082/api/order/Product/productBlock/{bookedQuantity}/{productId}";
		restTemplate.getForObject(url, String.class,bookedQuantity,productId);
		ResponseEntity<HttpStatus>status=restTemplate.getForEntity(url, HttpStatus.class,bookedQuantity,productId);
		if(status.getStatusCode().value()!=200) {
			throw new DataReceivedErrorException("Error in Blocking the Product after  Payment Failure");
		}
		
	}

	
	@Override
	public void productBlockRemoval(ProductDTO productDTO) 
	{
		LOGGER.info("Service Call to unBlock the product after successful payment");
		String url="http://localhost:8082/api/order/Product/productBlockRemoval";
		ResponseEntity<HttpStatus>status=restTemplate.postForEntity(url,productDTO, HttpStatus.class);
		if(status.getStatusCode().value()!=200) {
			throw new DataReceivedErrorException("Error in UnBlocking the Product after  Payment sucess");
		}
	}

	@Override
	public void productBlockRemovalAfterFailure(ProductDTO productDTO) {
		LOGGER.info("Service Call  to unBlock the product due to  payment failure");
		String url="http://localhost:8082/api/order/Product/productBlockRemovalPaymentFailure";
		ResponseEntity<HttpStatus>status=restTemplate.postForEntity(url,productDTO, HttpStatus.class);
		if(status.getStatusCode().value()!=200) {
			throw new DataReceivedErrorException("Error in UnBlocking the Product after  Payment Failure");
		}
	}

	
}

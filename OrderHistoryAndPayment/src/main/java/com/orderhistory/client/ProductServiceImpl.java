package com.orderhistory.client;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.orderhistory.dto.CartDTO;
import com.orderhistory.dto.ProductDTO;
import com.orderhistory.exceptionhandling.DataReceivedErrorException;

@Service
public class ProductServiceImpl implements ProductService 
{
	@Autowired	RestTemplate restTemplate;
	@Autowired   ProductDTO productDTO;
	
	@Override
	public ProductDTO getProductById(Long productId) {
		String url="http://localhost:8082/api/order/Product/editProduct/{productId}";
		ResponseEntity<ProductDTO> productDto=  restTemplate.getForEntity(url, ProductDTO.class,productId);
		if(productDto.getStatusCode()!=HttpStatus.OK){
			throw new DataReceivedErrorException("Error in finding the cartId from Cart Service"+productId);
		}
		return productDto.getBody();
	}

}

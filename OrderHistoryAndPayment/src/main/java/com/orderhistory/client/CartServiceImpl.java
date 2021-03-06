package com.orderhistory.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.orderhistory.dto.CartDTO;
import com.orderhistory.exceptionhandling.DataReceivedErrorException;

@Service
public class CartServiceImpl implements CartService
{
	@Autowired RestTemplate restTemplate;
	@Autowired CartDTO cartDTO;	
	private static final Logger LOGGER=LoggerFactory.getLogger(CartServiceImpl.class);
	
	@Override
	public CartDTO findByCartId(Long cartId) 
	{
		LOGGER.info("Service Call to Cart Service to get Address");
		String url="http://localhost:8084/api/order/ItemAndCart/getCart/{cartId}";
		ResponseEntity<CartDTO>cartDto= restTemplate.getForEntity(url, CartDTO.class,cartId);
		if(cartDto.getStatusCode()!=HttpStatus.OK){
			throw new DataReceivedErrorException("Error in finding the cartId from Cart Service"+cartId);
		}
		return cartDto.getBody();
	}

	@Override
	public void productBlockRemoval(CartDTO cartDTO) 
	{
		String url="http://localhost:8084/api/order/ItemAndCart/productBlockRemoval";
		ResponseEntity<HttpStatus>status=restTemplate.postForEntity(url,cartDTO, HttpStatus.class);
		if(status.getStatusCode().value()!=200) {
			throw new DataReceivedErrorException("Error in UnBlocking the Product after  Payment sucess");
		}
	}

	@Override
	public void productBlockRemovalPaymentFailure(CartDTO cartDTO) {
		String url="http://localhost:8084/api/order/ItemAndCart/productBlockRemovalPaymentFailure";
		ResponseEntity<HttpStatus>status=restTemplate.postForEntity(url,cartDTO, HttpStatus.class);
		if(status.getStatusCode().value()!=200) {
			throw new DataReceivedErrorException("Error in UnBlocking the Product after  Payment Failure");
		}
	}

}

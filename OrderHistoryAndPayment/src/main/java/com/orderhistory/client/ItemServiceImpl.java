package com.orderhistory.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.orderhistory.dto.CartDTO;
import com.orderhistory.exceptionhandling.DataReceivedErrorException;


@Service
public class ItemServiceImpl implements ItemService 
{	
	@Autowired RestTemplate restTemplate;
	@Autowired CartDTO cartDTO;	
	
	@Override
	public void removeRefernceOfItemAndAmountReductionInCart(CartDTO cartDTO) 
	{
		String url="http://localhost:8084/api/order/ItemAndCart/removeRefernceOfItem";
		ResponseEntity<HttpStatus>status=restTemplate.postForEntity(url,cartDTO, HttpStatus.class);
		if(status.getStatusCode().value()!=200) {
			throw new DataReceivedErrorException("Error in removing the item and amount reduction in cart");
		}
	}

}

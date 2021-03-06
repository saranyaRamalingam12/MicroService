package com.cart.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cart.dto.UserDTO;
import com.cart.exceptionhandling.DataReceivedErrorException;


@Service
public class LoginServiceImpl implements LoginService
{
	private static final Logger LOGGER=LoggerFactory.getLogger(LoginService.class);
	@Autowired
	RestTemplate restTemplate;

	@Override
	public UserDTO getUserById(Long userId) {
		LOGGER.info("Service Call to Login Service to get User");
		String url="http://localhost:8081/api/order/login/getUserById/{id}";
		ResponseEntity<UserDTO>userDto =restTemplate.getForEntity(url, UserDTO.class,userId);
		if(userDto.getStatusCode()!=HttpStatus.OK){
			throw new DataReceivedErrorException("Error in finding the userId from Login Service"+userId);
		}
		return userDto.getBody();
	}

	@Override
	public UserDTO saveCartIdInUser(UserDTO userDto) 
	{
		LOGGER.info("Service Call to Login Service to save CartID");
		String url="http://localhost:8081/api/order/login/saveCartIdInUser";
		ResponseEntity<UserDTO>userDTO=restTemplate.postForEntity(url,userDto, UserDTO.class);
		if(userDTO.getStatusCode().value()!=200) {
			throw new DataReceivedErrorException("Error in UnBlocking the Product after  Payment sucess");
		}
		return userDTO.getBody();
	}

}

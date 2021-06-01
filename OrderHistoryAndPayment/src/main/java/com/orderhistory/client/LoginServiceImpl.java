package com.orderhistory.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.orderhistory.dto.CartDTO;
import com.orderhistory.dto.UserDTO;
import com.orderhistory.exceptionhandling.DataReceivedErrorException;

@Service
public class LoginServiceImpl implements LoginService
{
	private static final Logger LOGGER=LoggerFactory.getLogger(LoginServiceImpl.class);
	@Autowired RestTemplate restTemplate;
	@Autowired UserDTO userDTO;

	@Override
	public UserDTO findUserById(Long userId) {
		LOGGER.info("Service Call to UserLogin Service to get Address");
		String url="http://localhost:8081/api/order/login/getUserById/{id}";
		ResponseEntity<UserDTO>userDto =restTemplate.getForEntity(url, UserDTO.class,userId);
		if(userDto.getStatusCode()!=HttpStatus.OK){
			throw new DataReceivedErrorException("Error in finding the userId from Login Service"+userId);
		}
		return userDto.getBody();
	}

	@Override
	public void mapAddressDTOToEntity(UserDTO userDTO) 
	{
		LOGGER.info("Service Call to UserLogin Service to save the  Address");
		String url="http://localhost:8081/api/order/login/updateAddressInUser";
		ResponseEntity<HttpStatus>status=restTemplate.postForEntity(url,userDTO, HttpStatus.class);
		if(status.getStatusCode().value()!=200) {
			throw new DataReceivedErrorException("Error in saving Address To User");
		}
	}

}

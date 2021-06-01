package com.orderhistory.client;

import org.springframework.stereotype.Service;

import com.orderhistory.dto.UserDTO;
import com.orderhistory.dto.UserDetailsDTO;

@Service
public interface LoginService {

	UserDTO findUserById(Long userId);

	void mapAddressDTOToEntity(UserDTO userDTO);
	
	

}

package com.cart.client;

import org.springframework.stereotype.Service;

import com.cart.dto.UserDTO;

@Service
public interface LoginService 
{
	public UserDTO  getUserById(Long id);

	public UserDTO saveCartIdInUser(UserDTO userDTO); 

}

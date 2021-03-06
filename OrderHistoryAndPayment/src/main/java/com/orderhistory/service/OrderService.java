package com.orderhistory.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.orderhistory.dto.CartDTO;
import com.orderhistory.dto.OrderDetailsDTO;
import com.orderhistory.dto.UserDTO;
import com.orderhistory.dto.UserDetailsDTO;
import com.orderhistory.entity.OrderDetails;

@Service
public interface OrderService 
{
	
	public UserDetailsDTO mapUserDTOToUserDetailsDTO(UserDTO userDTO,CartDTO cartDTO);

	public UserDTO mapUserDetailsDTOToUserDTO(UserDetailsDTO userDetailsDTO);

	public void saveOrderDetails(UserDetailsDTO userDetailsDTO);

	public List<OrderDetails> getOrderByCartId(Long userId);

	public List<OrderDetailsDTO> mapOrderToOrderDTO(List<OrderDetails> order);

	public List<OrderDetails> getAllOrders(); 

}

package com.orderhistory.service.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderhistory.client.LoginService;
import com.orderhistory.client.ProductService;
import com.orderhistory.dto.CartDTO;
import com.orderhistory.dto.ItemDTO;
import com.orderhistory.dto.OrderDetailsDTO;
import com.orderhistory.dto.ProductDTO;
import com.orderhistory.dto.UserDTO;
import com.orderhistory.dto.UserDetailsDTO;
import com.orderhistory.entity.OrderDetails;
import com.orderhistory.exceptionhandling.ResourceNotFoundException;
import com.orderhistory.repository.OrderHistoryRepo;
import com.orderhistory.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService 
{
	@Autowired UserDetailsDTO userDetailsDTO;
	@Autowired UserDTO userDTO;
	@Autowired OrderDetails orderDetails;
	@Autowired OrderDetailsDTO orderDetailsDTO;
	@Autowired OrderHistoryRepo orderRepo;
	@Autowired ProductDTO productDTO;
	@Autowired ProductService productService;
	@Autowired LoginService loginService;
		
	@Override
	public List<OrderDetails> getOrderByCartId(Long userId) {
		return orderRepo.findByuserId(userId);
	}

	@Override
	public UserDetailsDTO mapUserDTOToUserDetailsDTO(UserDTO userDTO, CartDTO cartDTO) {
		userDetailsDTO.setAddressDTO(userDTO.getAddressDTO());
		userDetailsDTO.setCartDTO(cartDTO);
		userDetailsDTO.setEmail(userDTO.getEmail());
		userDetailsDTO.setId(userDTO.getId());
		userDetailsDTO.setName(userDTO.getName());
		return userDetailsDTO;
	}

	@Override
	public UserDTO mapUserDetailsDTOToUserDTO(UserDetailsDTO userDetailsDTO) {
		userDTO.setAddressDTO(userDetailsDTO.getAddressDTO());
		userDTO.setCartId(userDetailsDTO.getCartDTO().getId());
		userDTO.setId(userDetailsDTO.getId());
		return userDTO;
	}

	@Override
	public void saveOrderDetails(UserDetailsDTO userDetailsDTO) {
		List<ItemDTO>itemList=userDetailsDTO.getCartDTO().getItemDTO();
		if(itemList.isEmpty())
			throw new ResourceNotFoundException("ItemDTO list is empty");
		for(ItemDTO item:itemList)
		{
			orderDetails=new OrderDetails();//check for scope
			LocalDateTime time=LocalDateTime.now();
			orderDetails.setBookedQuantity(item.getBookedQuantity());
			orderDetails.setOrderDate(time);
			orderDetails.setProductId(item.getProductDTO().getId());
			orderDetails.setUserId(userDetailsDTO.getId());
			orderDetails.setProductTotal(item.getProductTotal());
			orderRepo.save(orderDetails);
		}
	}

	@Override
	public List<OrderDetailsDTO> mapOrderToOrderDTO(List<OrderDetails> orderList) {
		  List<OrderDetailsDTO>orderDTOList=new ArrayList<>();
		  for(OrderDetails orderDetails:orderList) {
			  orderDetailsDTO=new OrderDetailsDTO();
			  orderDetailsDTO.setId(orderDetails.getId());
			  orderDetailsDTO.setBookedQuantity(orderDetails.getBookedQuantity());
			  orderDetailsDTO.setOrderDate(orderDetails.getOrderDate());
			  productDTO=productService.getProductById(orderDetails.getProductId());
			  orderDetailsDTO.setProductDTO(productDTO);
			  userDTO=loginService.findUserById(orderDetails.getUserId());
			  orderDetailsDTO.setUserDTO(userDTO);
			  orderDetailsDTO.setProductTotal(orderDetails.getProductTotal());
			  orderDTOList.add(orderDetailsDTO);
		  }
		  return orderDTOList;
	}

	@Override
	public List<OrderDetails> getAllOrders() {
		 return orderRepo.findAll();
	}

	
}
